package com.binod.project.swing.user;

import com.binod.project.swing.components.ChannelInformationMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
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

        ClassLoader cldr = LoadingPage.class.getClassLoader();
        URL url = LoadingPage.class.getResource("/loading.gif");
        if (url == null)
            url = cldr.getResource("/loading.gif");
        ImageIcon loading = new ImageIcon(url);

//        ImageIcon loading = new ImageIcon(new File(new File("pictures/loading.gif").getAbsolutePath()).toString());
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
                },4000
        );
    }

}
