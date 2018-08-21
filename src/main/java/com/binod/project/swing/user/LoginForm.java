
package com.binod.project.swing.user;

/**
 * Created by Binod Bhandari on 8/20/18.
 */
import com.binod.project.swing.components.Channel;

import java.awt.*;

import javax.swing.*;


public class LoginForm {

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
        loginBox.setPreferredSize(new Dimension(600,500));
        loginBox.setBackground(Color.WHITE);

        JLabel signInLabel = new JLabel("Sign in to your chat room");
        signInLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 400, 0));
        signInLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel enterYourCredentialLabel = new JLabel("Enter your credentials");
        signInLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 300, 0));
        enterYourCredentialLabel.setFont(new Font("Arial", Font.BOLD, 15));

        loginBox.add(signInLabel);
        loginBox.add(enterYourCredentialLabel);

        loginBox.add(logInPanel());

        return loginBox;
    }
    public LoginForm(Container container) {

        container.setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.anchor = GridBagConstraints.FIRST_LINE_START;
        con.gridx = 0;
        con.weightx = 1;
        con.gridy = 0;
        con.weighty = 1;
        con.gridwidth = 3;
        con.fill = GridBagConstraints.HORIZONTAL;
        container.add(getHeader(), con);
        con.anchor = GridBagConstraints.CENTER;
        con.gridx++;
        con.gridy++;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.NONE;
        container.add(getLoginBox(), con);
    }
    public static void main(String... args) {
        loginFrame = new JFrame("Login Window");
        LoginForm form = new LoginForm(loginFrame.getContentPane());
        loginFrame.setSize(700, 700);
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static JPanel logInPanel(){

        JPanel loginBox = new JPanel(new GridBagLayout());
        loginBox.setPreferredSize(new Dimension(400,400));
        loginBox.setBackground(Color.WHITE);

        JLabel signInLabel = new JLabel("Sign in to your chat room");
        signInLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 350, 0));
        signInLabel.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel panel = new JPanel(new GridLayout(2,3));
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

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton login = new JButton("Log In");

        login.addActionListener(e -> {
            if(usernameTextField.getText().equals("D") && passwordTextField.getText().equals("H")){
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

        labelPanel.setBackground(Color.WHITE);
        textPanel.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.WHITE);
        panel.add(labelPanel);
        panel.add(textPanel);
        panel.add(buttonPanel);
        panel.setBackground(Color.WHITE);
        return panel;
    }

}