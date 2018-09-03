package com.binod.project.swing.user;

/**
 * Created by Binod Bhandari on 7/30/18.
 */

import com.binod.project.swing.components.Channel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

@Data
public class SignUp {

    private static JTextField usernameTextField;
    private static JTextField passwordTextField;
    private static JFrame signUpFrame;
    private static JFrame SignUpSuccessMessageBox;

    private Connection connection;
    private Properties properties;

    @Value("binod.cs338.datasource.jdbcUrl")
    private String connectionString;

    @Value("binod.cs338.datasource.username")
    private String username;

    @Value("binod.cs338.datasource.password")
    private String password;

    private String query = "INSERT INTO USERDATA(username,password,email,phoneNumber) VALUES(?,?,?,?)";

    public SignUp(Container container) {

        try {
            properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        query = properties.getProperty("binod.cs338.datasource.query");
        connectionString = properties.getProperty("binod.cs338.datasource.jdbcUrl");
        username = properties.getProperty("binod.cs338.datasource.username");
        password = properties.getProperty("binod.cs338.datasource.password");

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
        GridBagConstraints bagConstraints = new GridBagConstraints();
        signUpBox.setPreferredSize(new Dimension(500,400));
        signUpBox.setBackground(Color.LIGHT_GRAY);

        JLabel signInLabel = new JLabel("Sign up to the chat room");
        signInLabel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 40));
        signInLabel.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel enterYourCredentialLabel = new JLabel("Enter your information");
        enterYourCredentialLabel.setBorder(BorderFactory.createEmptyBorder(0, 120, 20, 40));
        enterYourCredentialLabel.setFont(new Font("Arial", Font.BOLD, 15));
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        bagConstraints.ipady = 10;
        bagConstraints.fill = GridBagConstraints.HORIZONTAL;
        bagConstraints.gridwidth = 2;
        bagConstraints.weightx = 1;
        bagConstraints.anchor = GridBagConstraints.CENTER;
        signUpBox.add(signInLabel, bagConstraints);
        bagConstraints.gridy++;
        signUpBox.add(enterYourCredentialLabel, bagConstraints);

        bagConstraints.ipady = 0;
        bagConstraints.gridy++;
        bagConstraints.gridx = 0;
        bagConstraints.gridwidth = 1;
        bagConstraints.weightx = 0.3;
        bagConstraints.anchor = GridBagConstraints.PAGE_START;
        signUpBox.add(new JLabel("Username"), bagConstraints);
        bagConstraints.gridy++;
        signUpBox.add(new JLabel("Password"), bagConstraints);
        bagConstraints.gridy++;
        signUpBox.add(new JLabel("Re-Enter Password"), bagConstraints);
        bagConstraints.gridy++;
        signUpBox.add(new JLabel("Email"), bagConstraints);
        bagConstraints.gridy++;
        signUpBox.add(new JLabel(("Phone Number")), bagConstraints);

        JTextField usernameTextField = new JTextField();
        JPasswordField passwordTextField = new JPasswordField();
        JPasswordField reEnterPasswordTextField = new JPasswordField();
        JTextField emailTextField = new JTextField();
        JTextField phoneNumberTextField = new JTextField();

        bagConstraints.gridy = 2;
        bagConstraints.gridx = 1;
        bagConstraints.weightx = 0.7;
        signUpBox.add(usernameTextField, bagConstraints);

        bagConstraints.gridy++;
        signUpBox.add(passwordTextField, bagConstraints);

        bagConstraints.gridy++;
        signUpBox.add(reEnterPasswordTextField, bagConstraints);

        bagConstraints.gridy++;
        signUpBox.add(emailTextField, bagConstraints);

        bagConstraints.gridy++;
        signUpBox.add(phoneNumberTextField, bagConstraints);

        bagConstraints.ipady = 5;
        bagConstraints.gridy++;
        bagConstraints.gridx = 0;
        bagConstraints.gridwidth = 2;
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 0.3;
        bagConstraints.anchor = GridBagConstraints.CENTER;
        JLabel login = new JLabel("<html>Go back to Login <a href='#'>Log In</a></html>");
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signUpFrame.dispose();
                LogIn.main();            }
        });
        signUpBox.add(login, bagConstraints);
        bagConstraints.gridy++;
        bagConstraints.gridx = 0;
        bagConstraints.gridwidth = 1;
        bagConstraints.weightx = 0.5;
        bagConstraints.weighty = 0.7;
        bagConstraints.anchor = GridBagConstraints.PAGE_END;
        JButton cancelButton = new JButton("Cancel");
        signUpBox.add(cancelButton, bagConstraints);

        bagConstraints.gridx++;
        JButton signUpButton = new JButton("Sign Up");
        signUpBox.add(signUpButton, bagConstraints);

        cancelButton.addActionListener(e -> System.exit(0));

        signUpButton.addActionListener(e -> {
            if(!passwordTextField.getText().equals(reEnterPasswordTextField.getText())){
                JOptionPane.showMessageDialog(SignUpSuccessMessageBox, "Sorry password does not match");
            }else{
                try {
                    connection = DriverManager.getConnection(connectionString, username,password);
                    connection.createStatement();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    assert connection != null;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, String.valueOf(usernameTextField));
                    preparedStatement.setString(2, String.valueOf(passwordTextField));
                    preparedStatement.setString(3, String.valueOf(emailTextField));
                    preparedStatement.setString(4, String.valueOf(phoneNumberTextField));
                    preparedStatement.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        return signUpBox;
    }

    public static void maini(String... args) {
        signUpFrame = new JFrame("Sign Up Window");
        SignUp form = new SignUp(signUpFrame.getContentPane());
        signUpFrame.setSize(600, 480);
        signUpFrame.setVisible(true);
        signUpFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
