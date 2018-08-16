package com.binod.project.swing.components;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Binod Bhandari on 8/15/18.
 */
public class ChannelDateBorder {

    private static String currentDateAndTime() {
        Date dateAndTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss a");
        return simpleDateFormat.format(dateAndTime);
    }

    public JPanel dateTopofChannel(){
        JPanel dateAboveChannelPanel = new JPanel();
        dateAboveChannelPanel.setBorder(new TitledBorder(new EtchedBorder(), "--------------------------------------" +
                currentDateAndTime() + "------------------------------------------------"));

        return dateAboveChannelPanel;
    }

}
