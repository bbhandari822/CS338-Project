package com.binod.project.swing.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.stream.IntStream;

/**
 * Created by Binod Bhandari on 8/4/18.
 */

public class ThreadClients extends Thread{

    private static String line;
    private String clientName = null;
    private PrintStream printStream;
    private Socket socketForClient;
    private ThreadClients[] threads = null;
    private int clientThreadNumber;
    private String name;


    public ThreadClients(Socket clientSocket, ThreadClients[] threads) {
        this.socketForClient = clientSocket;
        this.threads = threads;
        clientThreadNumber = threads.length;
    }


    public void run() {
        int maxClientsCount = this.clientThreadNumber;
        ThreadClients[] threads = this.threads;

        try {
            DataInputStream dataInputStream = new DataInputStream(socketForClient.getInputStream());
            printStream = new PrintStream(socketForClient.getOutputStream());
            while (true) {
                printStream.println("Enter your name.");
                name = dataInputStream.readLine().trim();
                if (name.indexOf('@') == -1) {
                    break;
                } else {
                    printStream.println("The name should not contain '@' character.");
                }
            }

            printStream.println("Welcome " + name
                    + " to our chat room.\nTo leave enter /quit in a new line.");
            System.out.println("Adding this client to active client list " + name);
            synchronized (this) {
                if (IntStream.range(0, maxClientsCount).anyMatch(i -> threads[i] != null && threads[i] == this)) {
                    clientName = "@" + name;
                }
                int j = 0;
                while (j < maxClientsCount) {
                    if (threads[j] != null && threads[j] != this) {
                        threads[j].printStream.println("Let's welcome " + name + " to the chat room !!! ***");

                    }
                    j++;
                }
            }
            while (true) {
                line = dataInputStream.readLine();
                if (line.startsWith("exit")) {
                    break;
                }

                if (line.startsWith("@")) {
                    String[] words = line.split("\\s", 2);
                    if (words[1] != null) {
                        if (words[1].trim().isEmpty()) {
                            continue;
                        }
                        synchronized (this) {
                            int i = 0;
                            while (i < maxClientsCount) {
                                if (threads[i] != null && threads[i] != this
                                        && threads[i].clientName != null
                                        && threads[i].clientName.equals(words[0])) {
                                    threads[i].printStream.println("<" + name + "> " + words[1]);
                                    this.printStream.println(">" + name + "> " + words[1]);
                                    break;
                                }
                                i++;
                            }
                        }
                    }
                }
                else {
                    /* The message dataInputStream public, broadcast it to all other clients. */
                    synchronized (this) {
                        IntStream.range(0, maxClientsCount).filter(i -> threads[i] != null && threads[i].clientName != null)
                                .forEach(i -> threads[i].printStream.println("<" + name + "> " + line));
                    }
                }
            }
            synchronized (this) {
                IntStream.range(0, maxClientsCount).filter(i -> threads[i] != null && threads[i] != this
                        && threads[i].clientName != null).forEach(i -> threads[i].printStream.println(" <<< The user " + name
                        + " is leaving the chat room !!! >>>"));
            }
            printStream.println(">>>>>>> Sad seeing you leave " + name + " >>>>>>>");

            /*
             * Clean up. Set the current thread variable to null so that a new client
             * could be accepted by the server.
             */

            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }
            /*
             * Close the output stream, close the input stream, close the socket.
             */
            dataInputStream.close();
            printStream.close();
            socketForClient.close();
        } catch (IOException ignored) {
        }
    }
}

