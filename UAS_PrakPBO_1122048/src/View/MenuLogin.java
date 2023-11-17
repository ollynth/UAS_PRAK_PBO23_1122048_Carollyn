package src.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import src.Controller.Controller;
import src.Model.User;

public class MenuLogin {
    public MenuLogin() {
        loginUser();
    }

    private Controller cntrl = new Controller();
    private ArrayList<User> users = new ArrayList<User>();

    private void loginUser() {
        JFrame inpFrame = new JFrame();
        inpFrame.setTitle("Login");
        inpFrame.setSize(400, 400); 
        inpFrame.setLocationRelativeTo(null);
        inpFrame.setLayout(null);
        inpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 1. email
        JLabel emaiLabel = new JLabel("Email ");
        emaiLabel.setBounds(10, 10,100,20);
        JTextField emailField = new JTextField();
        emailField.setBounds(130, 10,160,20);
        inpFrame.add(emaiLabel);
        inpFrame.add(emailField);

        // 2. Password
        JLabel pwdLabel = new JLabel("Password ");
        pwdLabel.setBounds(10, 40,100,20);
        JPasswordField pwdField = new JPasswordField();
        pwdField.setBounds(130, 40,160,20);
        inpFrame.add(pwdLabel);
        inpFrame.add(pwdField);

        // 3. Button Login
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 70, 120, 20);
        inpFrame.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(pwdField.getPassword());
                String email = emailField.getText();
                int found = cntrl.getUser(email, password);

                if (found != 0) {
                    JOptionPane.showMessageDialog(inpFrame, "Login Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    inpFrame.dispose();
                    new MenuGameList(found);
                } else {
                    JOptionPane.showMessageDialog(inpFrame, "User Not Found", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        inpFrame.setVisible(true);
    }
}