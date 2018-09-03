package com.binod.project.swing.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import static com.sun.webkit.graphics.WCImage.getImage;

/**
 * Created by Binod Bhandari on 8/19/18.
 */
public class ChannelInformationMenu extends JFrame {

    //return the tool bar
    public ChannelInformationMenu() {

        initUI();
    }

    public final void initUI() {

        createToolBars();

        setTitle("Toolbars");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //imported the picture from a directory
    public JToolBar createToolBars() {
        //submenu for the toolbar.
        JToolBar submenu = new JToolBar();

        ClassLoader cldr = ChannelInformationMenu.class.getClassLoader();
        URL urlStar = ChannelInformationMenu.class.getResource("/star.jpg");
        if (urlStar == null)
            urlStar = cldr.getResource("/star.jpg");
        ImageIcon starIcon = new ImageIcon(urlStar);

        URL urlPeople = ChannelInformationMenu.class.getResource("/peopleicon.jpg");
        if (urlPeople == null)
            urlPeople = cldr.getResource("/peopleicon.jpg");
        ImageIcon peopleIcon = new ImageIcon(urlPeople);

        URL urlThumb = ChannelInformationMenu.class.getResource("/thumb.jpg");
        if (urlThumb == null)
            urlThumb = cldr.getResource("/thumb.jpg");
        ImageIcon thumbIcon = new ImageIcon(urlThumb);

        URL urlPhone = ChannelInformationMenu.class.getResource("/phone.jpg");
        if (urlPhone == null)
            urlPhone = cldr.getResource("/phone.jpg");
        ImageIcon phoneIcon = new ImageIcon(urlPhone);

        URL urlInfo = ChannelInformationMenu.class.getResource("/info.jpg");
        if (urlInfo == null)
            urlInfo = cldr.getResource("/info.jpg");
        ImageIcon infoIcon = new ImageIcon(urlInfo);

        URL urlSetting = ChannelInformationMenu.class.getResource("/setting.jpg");
        if (urlSetting == null)
            urlSetting = cldr.getResource("/setting.jpg");
        ImageIcon settingIcon = new ImageIcon(urlSetting);

        JButton starButton = new JButton(starIcon);
        JButton peopleButton = new JButton(peopleIcon);
        JButton thumbButton = new JButton(thumbIcon);

        JButton phoneButton = new JButton(phoneIcon);
        JButton infoButton = new JButton(infoIcon);
        JButton settingButton = new JButton(settingIcon);

        submenu.add(starButton);
        submenu.add(peopleButton);
        submenu.add(thumbButton);
        submenu.add(phoneButton);
        submenu.add(infoButton);
        submenu.add(settingButton);

        createLayout(submenu);
        return submenu;
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0], GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

}