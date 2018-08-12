package com.binod.project.swing.chat;

import java.net.Socket;

/**
 * Created by Binod Bhandari on 8/11/18.
 */
public class PersonalChat extends ThreadClients {

    public PersonalChat(Socket clientSocket, ThreadClients[] threads) {
        super(clientSocket, threads);
    }

}
