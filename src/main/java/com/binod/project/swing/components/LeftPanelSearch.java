package com.binod.project.swing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Binod Bhandari on 8/19/18.
 */
public class LeftPanelSearch {

    private static JFrame frame = new JFrame();
    private static JLayeredPane lpane = new JLayeredPane();
    private static JPanel memberListPanel = new JPanel();
    private static JPanel channelListPanel = new JPanel();


    public LeftPanelSearch() {
        channelListPanel.setBackground(Color.BLUE);
        channelListPanel.setBounds(-100, 300, 200, 300);
        memberListPanel.setBackground(Color.GREEN);
        memberListPanel.setBounds(0, 0, 300, 300);

    }


    private static JPanel channelListPanel(){

        channelListPanel = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setRows(100);
        textArea.setColumns(100);
        channelListPanel.add(textArea);

        return channelListPanel;
    }

    private static JPanel memberListPanel(){

        memberListPanel = new JPanel();
        return memberListPanel;
    }

    public JLayeredPane returnLeftPanel(JFrame frame){
        frame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 300, 300);
        lpane.setSize(100,100);
        lpane.add(memberListPanel, 0, 0);
        lpane.add(channelListPanel, 1, 0);
        return lpane;
    }

}
