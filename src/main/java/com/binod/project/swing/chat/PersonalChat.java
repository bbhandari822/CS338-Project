package com.binod.project.swing.chat;

import com.binod.project.swing.components.ChatAreaBox;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
 * Created by Binod Bhandari on 8/11/18.
 */
public class PersonalChat {

    static class ChatController extends Observable {
        private Socket socket;
        private OutputStream outputStream;

        @Override
        public void notifyObservers(Object arg) {
            super.setChanged();
            super.notifyObservers(arg);
        }

        public void initSocket() throws IOException {

            socket = new Socket("localhost", 3456);
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

        public void send(String text) throws IOException {
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

    static class ChatFrame extends JFrame implements Observer {

        private JTextArea textArea;
        private JTextField inputTextField;
        private JButton sendButton;
        private ChatController chatController;

        public ChatFrame(ChatController chatController) {
            this.chatController = chatController;
            chatController.addObserver(this);
            buildGUI();
        }

        /** Builds the user interface */
        private void buildGUI() {
            textArea = new JTextArea(20, 50);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            add(new JScrollPane(textArea), BorderLayout.CENTER);

            Box box = Box.createHorizontalBox();
            add(box, BorderLayout.SOUTH);
            inputTextField = new JTextField();
            sendButton = new JButton("Send");
            box.add(inputTextField);
            box.add(sendButton);

            // Action for the inputTextField and the goButton
            ActionListener sendListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String str = inputTextField.getText();
                    if (str != null && str.trim().length() > 0) {
                        try {
                            chatController.send(str);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    inputTextField.selectAll();
                    inputTextField.requestFocus();
                    inputTextField.setText("");
                }
            };
            inputTextField.addActionListener(sendListener);
            sendButton.addActionListener(sendListener);

//            this.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    chatController.close();
//                }
//            });
        }


        @Override
        public void update(Observable o, Object arg) {
            final Object finalArg = arg;
            SwingUtilities.invokeLater(() -> {
                textArea.append(finalArg.toString());
                textArea.append("\n");
            });
        }
    }

}
