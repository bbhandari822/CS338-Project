package main.java.assignment1.src;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Binod Bhandari on 6/24/18.
 */
public class Problem1 {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Problem 1");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(6,6));
        JPanel numberPanel = numberPanel();
        JPanel menuPanel = menuPanel();
        JPanel mainFramePanelRight = new JPanel();

        mainFramePanelRight.setLayout(new BorderLayout(6, 6));
        mainFramePanelRight.add(numberPanel, BorderLayout.CENTER);
        mainFramePanelRight.add(menuPanel, BorderLayout.SOUTH);

        mainPanel.add(labelAndButtonPanel(), BorderLayout.WEST);
        mainPanel.add(mainFramePanelRight, BorderLayout.CENTER);

        jFrame.add(mainPanel);
        jFrame.getContentPane();
        jFrame.pack();

        jFrame.setVisible(true);

    }

    private static JPanel numberPanel(){
        JPanel numberPanelInFrameLeft = new JPanel(new GridLayout(2, 1));
        JButton buttonOne = new JButton("1");
        JButton buttonTwo = new JButton("2");

        numberPanelInFrameLeft.add(buttonOne);
        numberPanelInFrameLeft.add(buttonTwo);

        JPanel numberPanelInFrameMiddle = new JPanel(new BorderLayout());
        JButton buttonThree = new JButton("3");
        JButton buttonFour = new JButton("4");
        JButton buttonFive = new JButton("5");

        numberPanelInFrameMiddle.add(buttonThree, BorderLayout.NORTH);
        numberPanelInFrameMiddle.add(buttonFour, BorderLayout.CENTER);
        numberPanelInFrameMiddle.add(buttonFive, BorderLayout.SOUTH);

        JPanel numberPanelInFrameRight = new JPanel(new GridLayout(2, 1));
        JButton buttonSix = new JButton("6");
        JButton buttonSeven = new JButton("7");

        numberPanelInFrameRight.add(buttonSix);
        numberPanelInFrameRight.add(buttonSeven);

        JPanel mainPanelNumber = new JPanel(new GridLayout(1, 3));
        mainPanelNumber.setAlignmentY(Component.CENTER_ALIGNMENT);

        mainPanelNumber.add(numberPanelInFrameLeft);
        mainPanelNumber.add(numberPanelInFrameMiddle);
        mainPanelNumber.add(numberPanelInFrameRight);

        return mainPanelNumber;
    }

    private static JPanel menuPanel(){

        JPanel menuPanelUnderNumber = new JPanel();
        menuPanelUnderNumber.setLayout(new BoxLayout(menuPanelUnderNumber, BoxLayout.X_AXIS));
        JButton helpButton = new JButton("Help");
        JButton advancedButton = new JButton("Advanced");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        menuPanelUnderNumber.add(helpButton);
        menuPanelUnderNumber.add(Box.createHorizontalGlue());
        menuPanelUnderNumber.add(advancedButton);
        menuPanelUnderNumber.add(Box.createHorizontalGlue());
        menuPanelUnderNumber.add(okButton);
        menuPanelUnderNumber.add(cancelButton);

        return menuPanelUnderNumber;
    }


    private static JPanel labelAndButtonPanel(){

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel labelAndButtonPanelLeft = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel shortLabel = new JLabel("Label");
        JLabel longLabel = new JLabel("LongLabel");

        JButton button1 = new JButton("Button1");
        JButton b2Button = new JButton("B2");
        JButton b3Button = new JButton("B3");

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        labelAndButtonPanelLeft.add(shortLabel,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        labelAndButtonPanelLeft.add(button1,gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        labelAndButtonPanelLeft.add(longLabel,gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        labelAndButtonPanelLeft.add(b2Button,gridBagConstraints);

        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        labelAndButtonPanelLeft.add(b3Button, gridBagConstraints);

        panel.add(labelAndButtonPanelLeft,BorderLayout.SOUTH);
        return panel;
    }

}
