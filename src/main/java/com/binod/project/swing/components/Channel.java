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
    private ChatAreaBox.ChatController access;
    private JPanel frame;


    //load the gif and delay the channel form.
    public void loadGifAndOpenChannel(){
        new LoadingPage().loading();
        Timer timer = new Timer();
        timer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            Channel.main();
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
        channelFrame.getContentPane().add(BorderLayout.NORTH, new ChannelInformationMenu().createToolBars());
        channelFrame.getContentPane().add(BorderLayout.WEST, new LeftPanelSearch().returnLeftPanel(channelFrame));
        channelFrame.getContentPane().add(BorderLayout.CENTER, frame);
        channelFrame.setVisible(true);

    }

    private void channelReturn() throws IOException{
        socket = new Socket("localhost", 3456);
        access = new ChatAreaBox.ChatController();
        frame = new ChatAreaBox.ChatPanel(access).getChatArea();
        access.initSocket(socket);
    }

    public static void main(String... args) throws IOException {
        Channel channel = new Channel();
        channel.channelReturn();
        channel.channelForm();
    }
}
