package com.binod.project.swing.user;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;

/**
 * Created by Binod Bhandari on 8/1/18.
 */

public class LoadingPage {

    public void loading(){

        JFrame loadingFrame = new JFrame("Please Wait!");
        ImageIcon loading = new ImageIcon(new File(new File("pictures/loading.gif").getAbsolutePath()).toString());
        loadingFrame.add(new JLabel(loading, JLabel.CENTER));
        loadingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadingFrame.setSize(550, 550);
        loadingFrame.setVisible(true);
        java.util.Timer timer = new Timer();
        timer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        loadingFrame.dispose();
                    }
                },5000
        );
    }

}
