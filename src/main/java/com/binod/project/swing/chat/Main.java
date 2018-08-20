package com.binod.project.swing.chat;

import com.binod.project.swing.chat.PersonalChat;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Binod Bhandari on 8/19/18.
 */
public class Main {

    public static void main(String[] args) {
        String server = "localhost";
        int port =3456;
        PersonalChat.ChatController access = new PersonalChat.ChatController();

        JFrame frame = new PersonalChat.ChatFrame(access);
        frame.setTitle("MyChatApp - connected to " + server + ":" + port);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        try {
            access.initSocket();
        } catch (IOException ex) {
            System.out.println("Cannot connect to " + server + ":" + port);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
