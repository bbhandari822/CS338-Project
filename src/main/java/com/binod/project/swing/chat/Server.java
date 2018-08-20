package com.binod.project.swing.chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Binod Bhandari on 8/11/18.
 */
public class Server {

    private static final ThreadClients[] threads = new ThreadClients[20];

    public void connectToServer() throws IOException{
        System.out.println("Group Chat Started");

        ServerSocket serverSocket = new ServerSocket(3456);

        do {
            try {
                Socket clientSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < 20; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new ThreadClients(clientSocket, threads)).start();
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.print(e.toString());
            }
        } while (true);
    }

    public static void main(String[] args) throws IOException {
        new Server().connectToServer();
    }
}
