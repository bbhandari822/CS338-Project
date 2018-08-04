package com.binod.project.swing.user;

import javax.swing.*;
import java.util.Timer;

/**
 * Created by Binod Bhandari on 8/1/18.
 */

public class LoadingPage {

    public void loading(){

        JFrame loadingFrame = new JFrame("Please Wait!");
        ImageIcon loading = new ImageIcon("src/main/java/com/binod/project/swing/pictures/loading.gif");
        loadingFrame.add(new JLabel(loading, JLabel.CENTER));
        loadingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loadingFrame.setSize(500, 500);
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
