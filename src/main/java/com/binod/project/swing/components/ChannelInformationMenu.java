package com.binod.project.swing.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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

        ImageIcon starIcon = new ImageIcon(new File(new File("pictures/star.jpg").getAbsolutePath()).toString());
        ImageIcon peopleIcon = new ImageIcon(new File(new File("pictures/peopleIcon.jpg").getAbsolutePath()).toString());
        ImageIcon thumbIcon = new ImageIcon(new File(new File("pictures/thumb.jpg").getAbsolutePath()).toString());

        ImageIcon phoneIcon = new ImageIcon(new File(new File("pictures/phone.jpg").getAbsolutePath()).toString());
        ImageIcon infoIcon = new ImageIcon(new File(new File("pictures/info.jpg").getAbsolutePath()).toString());
        ImageIcon settingIcon = new ImageIcon(new File(new File("pictures/setting.jpg").getAbsolutePath()).toString());

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