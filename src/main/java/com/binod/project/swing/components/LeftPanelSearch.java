package com.binod.project.swing.components;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Binod Bhandari on 8/19/18.
 */
public class LeftPanelSearch {

    private static JFrame frame = new JFrame();
    private static JLayeredPane lpane = new JLayeredPane();
    private static JPanel memberListPanel = new JPanel();
    private static JPanel channelListPanel = new JPanel();
    private static JTextArea channelAreaBox;
    private static JTextField channelTextField;
    private static JTextArea memberAreaBox;
    private static JTextField memberTextField;
    private static JFrame memberSuccessDialog;
    private static JFrame channelSuccessDialog;



    public LeftPanelSearch() {
        memberListPanel().setBorder(BorderFactory.createLineBorder(Color.GRAY));
        memberListPanel.setBounds(5, 20, 300, 350);
        memberListPanel.setBorder(new TitledBorder(new EtchedBorder(), "Members"));
        channelListPanel().setBorder(BorderFactory.createLineBorder(Color.GRAY));
        channelListPanel.setBounds(5, 400, 300, 345);
        channelListPanel.setBorder(new TitledBorder(new EtchedBorder(), "Channels"));

    }


    private static JPanel channelListPanel(){

        channelListPanel = new JPanel();
        channelAreaBox = new JTextArea(17, 22);
        channelAreaBox.setEditable(false);
        channelAreaBox.setText("Channel 1");
        JScrollPane jScrollPane = new JScrollPane(channelAreaBox);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        channelListPanel.add(jScrollPane, gridBagConstraints);

        channelTextField = new JTextField(15);
        channelTextField.addActionListener(e -> {
            String text = channelTextField.getText();
            getTextFromChannelTextField(text);
        });
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        channelListPanel.add(channelTextField, gridBagConstraints);
        JButton addChannel = new JButton("Add");
        addChannel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = channelTextField.getText();
                getTextFromChannelTextField(text);
            }
        });
        channelListPanel.add(addChannel);

        return channelListPanel;
    }

    private static void getTextFromChannelTextField(String text) {
        if(text.equals("")){
            JOptionPane.showMessageDialog(channelSuccessDialog,
                    "Channel name cannot be empty");
        }else {
            channelAreaBox.append(text + "\n");
            channelTextField.selectAll();
            channelAreaBox.setCaretPosition(channelAreaBox.getDocument().getLength());
            channelTextField.setText("");
        }
    }

    private static JPanel memberListPanel(){

        memberListPanel = new JPanel();
        memberAreaBox = new JTextArea(17, 22);
        memberAreaBox.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(memberAreaBox);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        memberListPanel.add(jScrollPane, gridBagConstraints);

        memberTextField = new JTextField(15);
        memberTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = memberTextField.getText();
                addTextFromMemberTextField(text);
            }
        });
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        memberListPanel.add(memberTextField, gridBagConstraints);
        JButton addMember = new JButton("Add");
        memberListPanel.add(addMember);
        addMember.addActionListener(e -> {
            String text = memberTextField.getText();
            addTextFromMemberTextField(text);
        });
        return memberListPanel;

    }

    private static void addTextFromMemberTextField(String text) {
        if(text.equals("")){
            JOptionPane.showMessageDialog(memberSuccessDialog,
                    "Member name cannot be empty");
        }else {
            memberAreaBox.append(text + "\n");
            memberTextField.selectAll();
            memberAreaBox.setCaretPosition(memberAreaBox.getDocument().getLength());
            memberTextField.setText("");
        }
    }

    public JLayeredPane returnLeftPanel(JFrame frame){
        lpane.setPreferredSize(new Dimension(320,320));

        frame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 300, 300);
        lpane.setSize(100,100);
        lpane.add(memberListPanel, 0, 0);
        lpane.add(channelListPanel, 1, 0);
        return lpane;
    }

}
