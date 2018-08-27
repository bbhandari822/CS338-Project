package com.binod.project.swing.user;

import com.binod.project.swing.components.Channel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Binod Bhandari on 7/30/18.
 */

public class LogIn {

    private static JTextField usernameTextField;
    private static JTextField passwordTextField;
    private static JFrame loginFrame;
    private static JFrame logInSuccessMessageBox;

    private JPanel getHeader() {

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(Color.WHITE);
        JLabel title = new JLabel("Drexel Chat Room");
        title.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        header.add(title);
        return header;
    }

    private JPanel getLoginBox() {
        JPanel loginBox = new JPanel(new GridBagLayout());
        loginBox.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridBagConstraints con = new GridBagConstraints();
        loginBox.setPreferredSize(new Dimension(400,250));
        loginBox.setBackground(Color.WHITE);

        JLabel signInLabel = new JLabel("Sign in to your chat room");
        signInLabel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 30));
        signInLabel.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel enterYourCredentialLabel = new JLabel("Enter your credentials");
        enterYourCredentialLabel.setBorder(BorderFactory.createEmptyBorder(0, 120, 20, 30));
        enterYourCredentialLabel.setFont(new Font("Arial", Font.BOLD, 15));
        con.gridx = 0;
        con.gridy = 0;
        con.ipady = 10;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridwidth = 2;
        con.weightx = 1;
        con.anchor = GridBagConstraints.CENTER;
        loginBox.add(signInLabel, con);
        con.gridy++;
        loginBox.add(enterYourCredentialLabel, con);

        con.ipady = 0;
        con.gridy++;
        con.gridx = 0;
        con.gridwidth = 1;
        con.weightx = 0.3;
        con.anchor = GridBagConstraints.PAGE_START;
        loginBox.add(new JLabel("Username"), con);
        con.gridy++;
        loginBox.add(new JLabel("Password"), con);

        JTextField usernameTextField = new JTextField();
        JPasswordField passwordTextField = new JPasswordField();
        con.gridy = 2;
        con.gridx = 1;
        con.weightx = 0.7;
        loginBox.add(usernameTextField, con);

        con.gridy++;
        loginBox.add(passwordTextField, con);

        con.ipady = 5;
        con.gridy++;
        con.gridx = 0;
        con.gridwidth = 2;
        con.weightx = 1;
        con.weighty = 0.3;
        con.anchor = GridBagConstraints.CENTER;
        JLabel login = new JLabel("<html>Don't have an account? <a href='#'>Sign Up</a></html>");
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.dispose();
                SignUp.main();
            }
        });

        loginBox.add(login, con);

        con.gridy++;
        con.gridx = 0;
        con.gridwidth = 1;
        con.weightx = 0.5;
        con.weighty = 0.7;
        con.anchor = GridBagConstraints.PAGE_END;
        //con.ipady = 10;
        JButton cancelButton = new JButton("Cancel");
        loginBox.add(cancelButton, con);

        con.gridx++;
        JButton loginButton = new JButton("Login");
        loginBox.add(loginButton, con);

        loginButton.addActionListener(e -> {
            if(usernameTextField.getText().equals("D") && passwordTextField.getText().equals("H")){
                if(validateLogIn(usernameTextField.getText(),passwordTextField.getText()))
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

        cancelButton.addActionListener(e -> System.exit(0));
        return loginBox;
    }

    private boolean validateLogIn(String text, String text1) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdata?" + "user=root&password=");
            PreparedStatement preparedStatement = conn.prepareStatement("Select * from USERDATA where username=? and password=?");
            preparedStatement.setString(1, usernameTextField.getText());
            preparedStatement.setString(2, passwordTextField.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public LogIn(Container container) {

        container.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.anchor = GridBagConstraints.FIRST_LINE_START;
        con.gridx = 0;
        con.weightx = 1;
        con.gridy = 0;
        con.weighty = 0.5;
        con.gridwidth = 3;
        con.fill = GridBagConstraints.HORIZONTAL;
        container.add(getHeader(), con);
        con.anchor = GridBagConstraints.CENTER;
        con.gridx++;
        con.gridy++;
        con.weightx = 1;
        con.weighty = 0.5;
        con.anchor = GridBagConstraints.PAGE_START;
        con.fill = GridBagConstraints.NONE;
        container.add(getLoginBox(), con);
    }
    public static void main(String... args) {
        loginFrame = new JFrame("Login Window");
        LogIn form = new LogIn(loginFrame.getContentPane());
        loginFrame.setSize(600, 480);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}