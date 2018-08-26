package com.binod.project.swing.components;

import com.binod.project.swing.chat.ThreadClients;
import com.binod.project.swing.user.LoadingPage;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;

/**
 * Created by Binod Bhandari on 8/4/18.
 */
@Data
public class Channel {

    private static Socket socket;
    private JFrame channelFrame;
    private static final ThreadClients[] threads = new ThreadClients[20];


    public void loadGifAndOpenChannel(){
        new LoadingPage().loading();
        Timer timer = new Timer();
        timer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            new Channel().channelReturn();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },4900
        );
    }

    private void channelForm() throws IOException {

        channelFrame = new JFrame("Channel 1");
        channelFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        channelFrame.setSize(1100, 880);
        channelFrame.setJMenuBar(new FrameMenuBar().showMenuBar(channelFrame));
        channelFrame.getContentPane().add(new ChannelInformationMenu().createToolBars(), BorderLayout.NORTH);
        channelFrame.getContentPane().add(BorderLayout.BEFORE_LINE_BEGINS, new ChannelInformationMenu().createToolBars());
        channelFrame.getContentPane().add(BorderLayout.CENTER, new ChatAreaBox().check(new ChatAreaBox.ChatController(), socket));
        channelFrame.setVisible(true);

    }

    private void channelReturn() throws IOException{
        Channel channel = new Channel();
        channel.channelForm();
    }

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 3456);
        new Channel().channelForm();
    }

}
