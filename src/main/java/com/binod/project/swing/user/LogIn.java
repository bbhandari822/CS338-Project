package com.binod.project.swing.user;

import com.binod.project.swing.components.Channel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import static com.binod.project.swing.user.SignUp.getPanel;

/**
 * Created by Binod Bhandari on 7/30/18.
 */
@PropertySource(value = { "classpath:application.properties" })
public class LogIn {

    private static JTextField usernameTextField;
    private static JTextField passwordTextField;
    private static JFrame loginFrame;
    private static JFrame logInSuccessMessageBox;

    @Value("${binod.cs338.datasource.username}")
    private String username;

    @Value("${binod.cs338.datasource.password}")
    private String password;

    @Value("${binod.cs338.datasource.jdbcUrl}")
    private String connectionString;

    private Properties properties;
    /*
    Opens the login panel/form
     */
    private JPanel getHeader() {

        return getPanel();
    }

    private JPanel getLoginBox() {
        JPanel loginBox = new JPanel(new GridBagLayout());
        loginBox.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        loginBox.setPreferredSize(new Dimension(400,350));
        loginBox.setBackground(Color.WHITE);

        JLabel signInLabel = new JLabel("Sign in to your chat room");
        signInLabel.setBorder(BorderFactory.createEmptyBorder(0, 100, 20, 30));
        signInLabel.setFont(new Font("Arial", Font.BOLD, 17));

        JLabel enterYourCredentialLabel = new JLabel("Enter your credentials");
        enterYourCredentialLabel.setBorder(BorderFactory.createEmptyBorder(0, 120, 20, 30));
        enterYourCredentialLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        loginBox.add(signInLabel, gridBagConstraints);
        gridBagConstraints.gridy++;
        loginBox.add(enterYourCredentialLabel, gridBagConstraints);

        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
        loginBox.add(new JLabel("Username"), gridBagConstraints);
        gridBagConstraints.gridy++;
        loginBox.add(new JLabel("Password"), gridBagConstraints);

        JTextField usernameTextField = new JTextField();
        JPasswordField passwordTextField = new JPasswordField();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 0.7;
        loginBox.add(usernameTextField, gridBagConstraints);

        gridBagConstraints.gridy++;
        loginBox.add(passwordTextField, gridBagConstraints);

        gridBagConstraints.ipady = 5;
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        JLabel login = new JLabel("<html>Don't have an account? <a href='#'>Sign Up</a></html>");
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginFrame.dispose();
                new SignUp(loginFrame).maini();
            }
        });

        loginBox.add(login, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        //gridBagConstraints.ipady = 10;
        JButton cancelButton = new JButton("Cancel");
        loginBox.add(cancelButton, gridBagConstraints);

        gridBagConstraints.gridx++;
        JButton loginButton = new JButton("Login");
        loginBox.add(loginButton, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = (int) 0.4;
        JButton aboutButton = new JButton("About");
        loginBox.add(aboutButton, gridBagConstraints);

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(logInSuccessMessageBox,
                        "<HTML> <b> About <b> \n" +
                        "\n" +
                         "<HTML> <b> Project Developed By: <b>" + "Binod Bhandari \n" +
                         "<HTML> <b> Project Version: <b>" + "1.0.0 \n" +
                                "\n" +
                         "1. Enter username and password to log in\n" +
                        "2. In case username and password is not in database click sign up\n" +
                        "    Once the data is saved to the database, click login label which will take\n" +
                        " back to the login page. Enter your credentials.\n" +
                        "3. Start the Server class\n" +
                        "4. Loading page will appear and after some time the channel page will open up. \n" +
                        "    It will fail to open if the server is not on. \"Group Chat Started\" message will appear in the console. \n" +
                        "5. Enter the name to start the channel, can run multiple instances on same time \n" +
                        "    to see a real-time message on different instances/thread. \n" +
                        "    \"Adding this client to active client list Test\" message will appear after entering \n" +
                        "the name. This will keep updated every time a new user is added. \n" +
                        "6. The left panel contains the channel and member panel where you can add the member name and \n" +
                        "    all the channels that you want to create. \n" +
                        "    Future: This can be updated from some database \n" +
                        "    (like Drexel classes and students enrolled in the class)\n" +
                        "7. Enter exit to stop/kill the thread and that thread will stop receiving the message.\n \n"
                        +
                                "<HTML> <i> Please refer to README for application setup: <i>" + "1.0.0 \n"
                );

            }
        });

        loginButton.addActionListener(e -> {
//            if(usernameTextField.getText().equals("Drexel") && passwordTextField.getText().equals("cs338")){

                //This is to get the data from database
            if(validateLogIn(usernameTextField.getText(),passwordTextField.getText())){
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

    private boolean validateLogIn(String userName, String userPassword) {
        try {
            Connection conn = DriverManager.getConnection(connectionString, username,password);
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM USERDATA WHERE username=(?) AND password=(?);");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public LogIn(Container container) {

        try {
            properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public static void main(String... args) {
        loginFrame = new JFrame("Login Window");
        LogIn form = new LogIn(loginFrame.getContentPane());
        loginFrame.setSize(600, 480);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}