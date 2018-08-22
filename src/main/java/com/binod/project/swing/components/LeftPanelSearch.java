package com.binod.project.swing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Binod Bhandari on 8/19/18.
 */
public class LeftPanelSearch {

    public LeftPanelSearch(Container contentPane) {
        JPanel channelViewPanel = new JPanel(new BorderLayout(6,6));

        channelViewPanel.add(channelListPanel(), BorderLayout.NORTH);
        channelViewPanel.add(memberListPanel(), BorderLayout.SOUTH);
    }


    public JPanel channelListPanel(){

        JPanel channelListPanel = new JPanel();
        return channelListPanel;
    }

    public JPanel memberListPanel(){

        JPanel memberListPanel = new JPanel();
        return memberListPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Left Panel");
        LeftPanelSearch form = new LeftPanelSearch(frame.getContentPane());
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

}
