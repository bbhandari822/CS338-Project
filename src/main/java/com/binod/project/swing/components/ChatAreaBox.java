package com.binod.project.swing.components;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Binod Bhandari on 8/3/18.
 */
public class ChatAreaBox {

    private JTextField textField;
    private JTextArea chatAreaBox;

    private static String getCurrentDateAndTime(){
        Date dateAndTime = new Date( );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss a");
        return simpleDateFormat.format(dateAndTime);
    }

    public JPanel getChatArea(){

        JPanel chatAreaPanel = new JPanel ();
        chatAreaPanel.setBorder (new TitledBorder( new EtchedBorder(), "--------------------------------------"+
                 getCurrentDateAndTime() + "------------------------------------------------"));

        chatAreaBox = new JTextArea ( 40, 50 );
        chatAreaBox.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(chatAreaBox);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        chatAreaPanel.add(jScrollPane, gridBagConstraints);

        textField = new JTextField(50);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                chatAreaBox.append(text + "\n");
                textField.selectAll();
                chatAreaBox.setCaretPosition(chatAreaBox.getDocument().getLength());
            }
        });
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        chatAreaPanel.add(textField,gridBagConstraints );

        return chatAreaPanel;

    }

}
