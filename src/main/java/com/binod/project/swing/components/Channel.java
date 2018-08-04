package com.binod.project.swing.components;

import com.binod.project.swing.user.LoadingPage;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

/**
 * Created by Binod Bhandari on 8/4/18.
 */
public class Channel {

    private JFrame channelFrame;

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

        channelFrame = new JFrame("Channel 1");
        channelFrame.setSize(600,600);
        channelFrame.setLayout(new GridLayout(3, 1));

        channelFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        channelFrame.setVisible(true);

    }

    private void showMenuBar(){

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
        JMenuItem toggleFullScreen = new JMenuItem("Toggle Full Screen");
        JMenuItem actualSize = new JMenuItem("Actual Size");
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
        windowMenu.add(minimize);

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

        channelFrame.setJMenuBar(channelMenuBar);
        channelFrame.setVisible(true);

    }

    public static void main(String[] args) {

        Channel channel = new Channel();
        channel.channelForm();
        channel.showMenuBar();
    }
}
