package com.binod.project.swing.components;

import com.binod.project.swing.user.LoadingPage;

import javax.swing.*;
import java.util.Timer;

/**
 * Created by Binod Bhandari on 8/4/18.
 */
public class Channel {

    public void loadGifAndOpenChannel(){
        new LoadingPage().loading();
        Timer timer = new Timer();
        timer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        new Channel().channelForm();
                    }
                },4900
        );
    }

    private void channelForm(){

        JFrame channelFrame = new JFrame("Channel 1");
        channelFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel channelPanel = new JPanel();

        channelFrame.add(channelPanel);
        channelFrame.setSize(600,600);
        channelFrame.setVisible(true);

    }
}
