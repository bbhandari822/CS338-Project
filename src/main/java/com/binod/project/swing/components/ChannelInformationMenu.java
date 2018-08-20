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

        ImageIcon newIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/info.jpg");
        ImageIcon openIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/save.jpg");
        ImageIcon saveIcon = new ImageIcon("src/main/java/com/binod/project/swing/pictures/thumb.jpg");

        JButton newBtn = new JButton(newIcon);
        JButton openBtn = new JButton(openIcon);
        JButton saveBtn = new JButton(saveIcon);

        submenu.add(newBtn);
        submenu.add(openBtn);
        submenu.add(saveBtn);

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