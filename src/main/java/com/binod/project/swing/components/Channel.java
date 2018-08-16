package com.binod.project.swing.components;

import com.binod.project.swing.chat.Server;
import com.binod.project.swing.user.LoadingPage;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;

/**
 * Created by Binod Bhandari on 8/4/18.
 */
@Data
public class Channel {

    private JFrame channelFrame;

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
        channelFrame.setSize(820, 850);
        channelFrame.getContentPane().add(BorderLayout.NORTH, new FrameMenuBar().showMenuBar(channelFrame));
        channelFrame.getContentPane().add(BorderLayout.CENTER, new ChatAreaBox().check());
        channelFrame.setVisible(true);

    }

    private void channelReturn() throws IOException{
        Channel channel = new Channel();
        channel.channelForm();
        new Server().connectToServer();
    }

    public static void main(String[] args) throws IOException {
        new Channel().channelReturn();
    }

}
