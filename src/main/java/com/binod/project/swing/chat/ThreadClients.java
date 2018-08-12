package com.binod.project.swing.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.stream.IntStream;

/**
 * Created by Binod Bhandari on 8/11/18.
 */
public class ThreadClients extends Thread {

    private Socket socketClient;
    private ThreadClients[] threadClients;
    private int clientThreadCount;
    private String clientName;
    private PrintStream printStream;
    private static String message;


    public void run() {
        int clientThreadCount = this.clientThreadCount;
        ThreadClients[] threadClients = this.threadClients;


        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(socketClient.getInputStream());
            printStream = new PrintStream(socketClient.getOutputStream());
            String name;
            do {
                printStream.println("Enter your name");
                name = dataInputStream.readLine().trim();
                if (name.indexOf('@') == -1) break;
                else printStream.println("The name should not contain '@' character.");
            } while (true);

            printStream.println(name + ", Welcome to Drexel Chat Room!");
            synchronized (this) {
                if (IntStream.range(0, clientThreadCount).anyMatch(i -> threadClients[i] != null && threadClients[i] == this)) {
                    clientName = "@" + name;
                }
            }

            int j = 0;
            while (j < clientThreadCount) {
                if (threadClients[j] != null && threadClients[j] != this) {
                    threadClients[j].printStream.println("New user " + name + " is added!");
                }
                j++;
            }

            while (true){
                 String message = dataInputStream.readLine();
                if(message.startsWith(String.valueOf("exit"))){
                    printStream.println("Sad seeing you leave " + name);
                    break;
                }
                 if(message.startsWith("@")){
                     String[] messageFirstWord = message.split("\\s", 2);
                     if(messageFirstWord[1] != null){
                         if(messageFirstWord[1].trim().isEmpty()){
                             continue;
                         }
                         synchronized (this) {
                             int i = 0;
                             while (i < clientThreadCount) {
                                 if (threadClients[i] != null && threadClients[i] != this
                                         && threadClients[i].clientName.equals(messageFirstWord[0]) && threadClients[i].clientName != null) {
                                     threadClients[i].printStream.println("<<" + name + ">> " + messageFirstWord[1]);
                                     this.printStream.println(">>" + name + ">> " + messageFirstWord[1]);
                                     break;
                                 }
                                 i++;
                             }
                         }
                     }
                 } else{
                     synchronized (this) {
                         String finalName = name;
                         IntStream.range(0, clientThreadCount).filter(i -> threadClients[i] != null && threadClients[i].clientName != null)
                                 .forEach(i -> threadClients[i].printStream.println("<" + finalName + "> " + message));
                     }
                 }
            }
            synchronized (this) {
                String finalName1 = name;
                IntStream.range(0, clientThreadCount).filter(i -> threadClients[i] != null && threadClients[i] != this
                        && threadClients[i].clientName != null).forEach(i -> threadClients[i].printStream
                        .println(">>>> User " + finalName1 + " is leaving the Drexel chat room !!!"));
            }

            synchronized (this) {
                for (int i = 0; i < 20; i++) {
                    if (threadClients[i] == this) {
                        threadClients[i] = null;
                    }
                }
            }

            printStream.close();
            dataInputStream.close();
            socketClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ThreadClients(Socket clientSocket, ThreadClients[] threads) {
        this.socketClient = clientSocket;
        this.threadClients = threads;
        clientThreadCount = threads.length;

    }
}
