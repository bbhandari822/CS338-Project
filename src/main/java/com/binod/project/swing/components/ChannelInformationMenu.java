package com.binod.project.swing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Binod Bhandari on 8/19/18.
 */
public class ChannelInformationMenu extends JFrame {

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

    public JToolBar createToolBars() {

        JToolBar submenu = new JToolBar();

        ImageIcon starIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/star.jpg");
        ImageIcon peopleIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/peopleIcon.jpg");
        ImageIcon thumbIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/thumb.jpg");

        ImageIcon phoneIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/phone.jpg");
        ImageIcon infoIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/info.jpg");
        ImageIcon settingIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/setting.jpg");

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