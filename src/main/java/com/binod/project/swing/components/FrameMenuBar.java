package com.binod.project.swing.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Binod Bhandari on 8/15/18.
 */
public class FrameMenuBar {

    private JFrame channelFrame;
    public JMenuBar showMenuBar(JFrame channelFrame) {
        this.channelFrame = channelFrame;
        return showMenuBar();
    }

    public JMenuBar showMenuBar(){

        final JMenuBar channelMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu historyMenu = new JMenu("History");
        JMenu windowMenu = new JMenu("Window");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem closeApplication = new JMenuItem("Close Application");
        closeApplication.addActionListener(e -> System.exit(0));

        fileMenu.add(closeApplication);

        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem pasteAndMatchStyle = new JMenuItem("Paste and Match Style");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem selectAll = new JMenuItem("Select All");

        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(pasteAndMatchStyle);
        editMenu.add(delete);
        editMenu.add(selectAll);


        JMenuItem reload = new JMenuItem("Reload");
        reload.addActionListener(e -> SwingUtilities.updateComponentTreeUI(channelFrame));
        JMenuItem toggleFullScreen = new JMenuItem("Toggle Full Screen");
        JMenuItem actualSize = new JMenuItem("Actual Size");
        actualSize.addActionListener(e -> {
            channelFrame.setPreferredSize(new Dimension(1120, 850));
            channelFrame.pack();
            channelFrame.setVisible(true);
        });
        JMenuItem zoomIn = new JMenuItem("Zoom In");
        JMenuItem zoomOut = new JMenuItem("Zoom Out");


        viewMenu.add(reload);
        viewMenu.add(toggleFullScreen);
        viewMenu.add(actualSize);
        viewMenu.add(zoomIn);
        viewMenu.add(zoomOut);

        JMenuItem back = new JMenuItem("Back");
        JMenuItem forward = new JMenuItem("Forward");

        historyMenu.add(back);
        historyMenu.add(forward);

        JMenuItem minimize = new JMenuItem("Minimize");
        minimize.addActionListener(e -> channelFrame.setState(Frame.ICONIFIED));
        JMenuItem maximize = new JMenuItem("Maximize");
        maximize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                channelFrame.setExtendedState(channelFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                channelFrame.pack();
                channelFrame.setVisible(true);
            }
        });
        windowMenu.add(minimize);
        windowMenu.add(maximize);

        JMenuItem keyboardShortcuts = new JMenuItem("Keyboard Shortcuts");
        JMenuItem helpCenter = new JMenuItem("Help Center");
        JMenuItem whatsNew = new JMenuItem("What's New...");


        helpMenu.add(keyboardShortcuts);
        helpMenu.add(helpCenter);
        helpMenu.add(whatsNew);

        channelMenuBar.add(fileMenu);
        channelMenuBar.add(editMenu);
        channelMenuBar.add(viewMenu);
        channelMenuBar.add(historyMenu);
        channelMenuBar.add(windowMenu);
        channelMenuBar.add(helpMenu);

        return channelMenuBar;

    }
}
