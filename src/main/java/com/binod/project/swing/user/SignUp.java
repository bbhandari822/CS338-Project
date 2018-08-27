package com.binod.project.swing.user;

/**
 * Created by Binod Bhandari on 7/30/18.
 */

import com.binod.project.swing.components.Channel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SignUp {

    private static JTextField usernameTextField;
    private static JTextField passwordTextField;
    private static JFrame signUpFrame;
    private static JFrame SignUpSuccessMessageBox;
    private Connection connection;

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
        JPanel signUpBox = new JPanel(new GridBagLayout());
        signUpBox.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridBagConstraints con = new GridBagConstraints();
        signUpBox.setPreferredSize(new Dimension(500,400));
        signUpBox.setBackground(Color.LIGHT_GRAY);

        JLabel signInLabel = new JLabel("Sign up to the chat room");
        signInLabel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 40));
        signInLabel.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel enterYourCredentialLabel = new JLabel("Enter your information");
        enterYourCredentialLabel.setBorder(BorderFactory.createEmptyBorder(0, 120, 20, 40));
        enterYourCredentialLabel.setFont(new Font("Arial", Font.BOLD, 15));
        con.gridx = 0;
        con.gridy = 0;
        con.ipady = 10;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridwidth = 2;
        con.weightx = 1;
        con.anchor = GridBagConstraints.CENTER;
        signUpBox.add(signInLabel, con);
        con.gridy++;
        signUpBox.add(enterYourCredentialLabel, con);

        con.ipady = 0;
        con.gridy++;
        con.gridx = 0;
        con.gridwidth = 1;
        con.weightx = 0.3;
        con.anchor = GridBagConstraints.PAGE_START;
        signUpBox.add(new JLabel("Username"), con);
        con.gridy++;
        signUpBox.add(new JLabel("Password"), con);
        con.gridy++;
        signUpBox.add(new JLabel("Re-Enter Password"), con);
        con.gridy++;
        signUpBox.add(new JLabel("Email"), con);
        con.gridy++;
        signUpBox.add(new JLabel(("Phone Number")), con);

        JTextField usernameTextField = new JTextField();
        JPasswordField passwordTextField = new JPasswordField();
        JPasswordField reEnterPasswordTextField = new JPasswordField();
        JTextField emailTextField = new JTextField();
        JTextField phoneNumberTextField = new JTextField();

        con.gridy = 2;
        con.gridx = 1;
        con.weightx = 0.7;
        signUpBox.add(usernameTextField, con);

        con.gridy++;
        signUpBox.add(passwordTextField, con);

        con.gridy++;
        signUpBox.add(reEnterPasswordTextField, con);

        con.gridy++;
        signUpBox.add(emailTextField, con);

        con.gridy++;
        signUpBox.add(phoneNumberTextField, con);

        con.ipady = 5;
        con.gridy++;
        con.gridx = 0;
        con.gridwidth = 2;
        con.weightx = 1;
        con.weighty = 0.3;
        con.anchor = GridBagConstraints.CENTER;
        JLabel signup = new JLabel("<html>Don't have an account? <a href='#'>Sign Up</a></html>");
//        signUpBox.add(signup, con);

        con.gridy++;
        con.gridx = 0;
        con.gridwidth = 1;
        con.weightx = 0.5;
        con.weighty = 0.7;
        con.anchor = GridBagConstraints.PAGE_END;
        JButton cancelButton = new JButton("Cancel");
        signUpBox.add(cancelButton, con);

        con.gridx++;
        JButton signUpButton = new JButton("Sign Up");
        signUpBox.add(signUpButton, con);

        cancelButton.addActionListener(e -> System.exit(0));

        signUpButton.addActionListener(e -> {
            if(!passwordTextField.getText().equals(reEnterPasswordTextField.getText())){
                JOptionPane.showMessageDialog(SignUpSuccessMessageBox, "Sorry password does not match");
            }else{
                String url = "jdbc:mysql://localhost:8181/userData";
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, "user", "user");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                String sql = "INSERT INTO USERDATA(username,password,email,phoneNumber) VALUES(?,?,?,?)";

                try {
                    assert connection != null;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, String.valueOf(usernameTextField));
                    preparedStatement.setString(2, String.valueOf(passwordTextField));
                    preparedStatement.setString(3, String.valueOf(emailTextField));
                    preparedStatement.setString(4, String.valueOf(phoneNumberTextField));
                    preparedStatement.executeUpdate(sql);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        return signUpBox;
    }
    public SignUp(Container container) {

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
        signUpFrame = new JFrame("Sign Up Window");
        SignUp form = new SignUp(signUpFrame.getContentPane());
        signUpFrame.setSize(600, 480);
        signUpFrame.setVisible(true);
        signUpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
