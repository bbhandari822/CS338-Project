package com.binod.project.swing.user;

import com.binod.project.swing.components.Channel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Binod Bhandari on 7/30/18.
 */

public class LogIn {

    private static JTextField usernameTextField;
    private static JTextField passwordTextField;
    private static JFrame loginFrame;
    private static JFrame logInSuccessMessageBox;


    public void loginVerification(){

        loginFrame = new JFrame("LogIn");
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainloginPanel = new JPanel();
        mainloginPanel.add(logInPanel());
        mainloginPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        loginFrame.add(mainloginPanel);
        loginFrame.getContentPane();
        loginFrame.setSize(600,600);
        loginFrame.pack();
        loginFrame.setVisible(true);

    }

    private static JPanel logInPanel(){

        JPanel panel = new JPanel(new GridLayout(2, 1));
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        labelPanel.add(usernameLabel, BorderLayout.NORTH);
        labelPanel.add(passwordLabel, BorderLayout.SOUTH);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        usernameTextField = new JTextField();
        passwordTextField = new JPasswordField();

        textPanel.add(usernameTextField);
        textPanel.add(passwordTextField);

        JPanel buttonPanel = new JPanel();
        JButton login = new JButton("Log In");

        login.addActionListener(e -> {
            if(new LogIn().getUsername().equals("D") && new LogIn().getPassword().equals("H")){
                JOptionPane.showMessageDialog(logInSuccessMessageBox, "Log in successful!");
                loginFrame.dispose();
                new Channel().loadGifAndOpenChannel();
            }else if(usernameTextField.getText().equals("") || passwordTextField.getText().equals("")){
                JOptionPane.showMessageDialog (null, "Please fill out all the information!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog (null, "Username or Password does not match!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JButton signup = new JButton("Sign Up");

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> System.exit(0));
        buttonPanel.add(login);
        buttonPanel.add(signup);
        buttonPanel.add(cancel);
        buttonPanel.add(Box.createHorizontalGlue());

        panel.add(labelPanel, Component.LEFT_ALIGNMENT);
        panel.add(textPanel,Component.RIGHT_ALIGNMENT);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    private String getUsername() {
        return usernameTextField.getText().trim();
    }

    private String getPassword() {
        return passwordTextField.getText().trim();
    }

}
