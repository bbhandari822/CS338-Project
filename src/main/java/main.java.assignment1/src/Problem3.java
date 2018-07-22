package main.java.assignment1.src;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;


/**
 * Created by Binod Bhandari on 6/24/18.
 * Email: bb822@drexel.edu
 * CS338:GUI, Assignment I
 */
public class Problem3 implements KeyListener{

    private float totalTime;
    private float keyReleased;
    private long keyPressed;

    private void cellPhoneProblem() {

        final JFrame frame = new JFrame("Program 3");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
//        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JLabel jLabel = new JLabel("Your average hold time is: Unknown");

        JTextField jTextField = new JTextField();
        jTextField.addActionListener((e -> jLabel.setText("Your average hold time is: " + (totalTime / keyReleased) + " ms")));

        jPanel.add(jLabel);
        jTextField.addKeyListener(this);
        jPanel.add(jTextField);
        frame.add(jPanel);
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {

        Problem3 problem3 = new Problem3();
        problem3.cellPhoneProblem();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Calendar cal = Calendar.getInstance();
        keyPressed = cal.getTime().getTime();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyReleased++;
        Calendar cal = Calendar.getInstance();
        long time = cal.getTime().getTime();
        totalTime += time - keyPressed;
    }


}
