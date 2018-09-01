package com.binod.project.swing.components;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Binod Bhandari on 8/3/18.
 */
public class ChatAreaBox {

    static class ChatController extends Observable{
        private Socket socket;
        private OutputStream outputStream;

        @Override
        public void notifyObservers(Object arg) {
            super.setChanged();
            super.notifyObservers(arg);
        }

        public void initSocket(Socket _socket) throws IOException {

            socket = _socket;
            outputStream = socket.getOutputStream();

            Thread thread = new Thread(() -> {
                try {
                    //get the input stream from the current socket.
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    bufferedReader.lines().forEach(this::notifyObservers);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        public void send(String text) {
            try {
                outputStream.write((text + "\r\n").getBytes());
                outputStream.flush();
                //flush the messages
            } catch (IOException ex) {
                notifyObservers(ex);
            }
        }

        //close the socket connection.
        public void close() {
            try {
                socket.close();
            } catch (IOException e) {
                notifyObservers(e);
            }
        }
    }

    static class ChatPanel extends JFrame implements Observer {

        private JTextField textField;
        private JTextArea chatAreaBox;
        private ChatController chatController;
        private JButton send;
        private JPanel chatAreaPanel;

        public ChatPanel(ChatController chatController) {
            this.chatController = chatController ;
            chatController.addObserver(this);
            getChatArea();
        }

        private static String getCurrentDateAndTime() {
            Date dateAndTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a");
            return simpleDateFormat.format(dateAndTime);
        }

        public JPanel getChatArea() {

            chatAreaPanel = new JPanel();
            chatAreaPanel.setBorder(new TitledBorder(new EtchedBorder(), "--------------------------------------" +
                    getCurrentDateAndTime() + "------------------------------------------------"));

            chatAreaBox = new JTextArea(45, 55);
            chatAreaBox.setEditable(false);
            JScrollPane jScrollPane = new JScrollPane(chatAreaBox);
            jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            chatAreaPanel.add(jScrollPane, gridBagConstraints);

            textField = new JTextField(49);
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            chatAreaPanel.add(textField, gridBagConstraints);
            send = new JButton("Send");
            chatAreaPanel.add(send);
            add(chatAreaPanel);
            ActionListener sendActionListener = e -> {
                String str = textField.getText();
                if (str != null && str.trim().length() > 0)
                    chatController.send(str);
                textField.selectAll();
                textField.requestFocus();
                textField.setText("");
            };

            textField.addActionListener(sendActionListener);
            send.addActionListener(sendActionListener);
            Component component = chatAreaPanel;
            while (component != null && ! (component instanceof Window))
                component = component.getParent();
            if (component != null) {
                ((Window) component).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        chatController.close();
                    }
                });
            }

            return chatAreaPanel;
        }


        @Override
        public void update(Observable o, Object arg) {
            final Object finalArg = arg;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    chatAreaBox.append(finalArg.toString());
                    chatAreaBox.append("\n");
                }
            });
        }
    }

}
