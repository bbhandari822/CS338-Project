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
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    bufferedReader.lines().forEach(this::notifyObservers);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        public void send(String text, Socket socket) throws IOException {
            initSocket(socket);
            try {
                outputStream.write((text + "\r\n").getBytes());
                outputStream.flush();
            } catch (IOException ex) {
                notifyObservers(ex);
            }
        }

        public void close() {
            try {
                socket.close();
            } catch (IOException e) {
                notifyObservers(e);
            }
        }
    }

    static class ChatPanel implements Observer {

        private JTextField textField;
        private JTextArea chatAreaBox;
        private ChatController chatController;
        private JPanel chatAreaPanel;

        public ChatPanel(ChatController chatController, Socket socket) throws IOException {
            this.chatController = chatController ;
            chatController.addObserver(this);
            getChatArea(chatController,socket);
        }

        private static String getCurrentDateAndTime() {
            Date dateAndTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a");
            return simpleDateFormat.format(dateAndTime);
        }

        public JPanel getChatArea(ChatController chatController, Socket socket) throws IOException {

            chatAreaPanel = new JPanel();
            chatAreaPanel.setBorder(new TitledBorder(new EtchedBorder(), "--------------------------------------" +
                    getCurrentDateAndTime() + "------------------------------------------------"));

            chatAreaBox = new JTextArea(45, 65);
            chatAreaBox.setEditable(false);
            JScrollPane jScrollPane = new JScrollPane(chatAreaBox);
            jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            chatAreaPanel.add(jScrollPane, gridBagConstraints);

            textField = new JTextField(59);
            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = textField.getText();
                    chatAreaBox.append(text + "\n");
                    textField.selectAll();
                    chatAreaBox.setCaretPosition(chatAreaBox.getDocument().getLength());
                    textField.setText("");
                }
            });
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            chatAreaPanel.add(textField, gridBagConstraints);
            JButton send = new JButton("Send");
            chatAreaPanel.add(send);

            ActionListener sendActionListener = (ActionEvent e) -> {
                String messageString = textField.getText();
                if (messageString.trim().length() > 0) {
                    try {
                        chatController.send(messageString, socket);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                textField.selectAll();
                textField.requestFocus();
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

    public JPanel check(ChatController chatController, Socket socket) throws IOException {
        JPanel chat = new ChatPanel(chatController, socket).getChatArea(chatController,socket);
        return chat;
    }

}
