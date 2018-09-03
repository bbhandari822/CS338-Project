package com.binod.project.swing.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Binod Bhandari on 8/11/18.
 */
public class Server {

    private static final ThreadClients[] threads = new ThreadClients[20];

    public void connectToServer() throws IOException{
        System.out.println("Group Chat Started");

        //server socket connection
        //listens to port 3456
        ServerSocket serverSocket = new ServerSocket(3456);
        do {
            try {
                Socket clientSocket = serverSocket.accept();
                int i = 0;
                //checks if the client Socket is null or not.
                if (clientSocket != null) {
                    //start the thread for every instances.
                    (threads[i] = new ThreadClients(clientSocket, threads)).start();
                    i++;
                }

            } catch (IOException e) {
                System.out.print(e.toString());
            }
        } while (true);
    }
//    public static void main(String[] args) throws IOException {
//        new Server().connectToServer();
//    }
}
