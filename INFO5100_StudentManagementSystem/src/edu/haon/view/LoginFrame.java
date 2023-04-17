package edu.haon.view;

import edu.haon.dao.AdminDao;
import edu.haon.model.Admin;
import edu.haon.util.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    private JPanel contentPanel;
    private JLabel user_lb;
    private JTextField user_tf;
    private JLabel password_lb;
    private JPasswordField password_pf;
    private JLabel userType_lb;
    private JComboBox userType_cb;
    private JButton login_button;
    private JButton reset_button;
    private JLabel title_lb;

    public LoginFrame(){
        setContentPane(contentPanel);
        setTitle("Welcome to Student Management System");
        setSize(500,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        /*
         * brief: ActionListener for login
         */
        login_button.addActionListener(e-> {
            String username = user_tf.getText().toString();
            String password = new String(password_pf.getPassword());
            int userType = userType_cb.getSelectedIndex();

            if(StringUtil.isEmpty(username)){
                JOptionPane.showMessageDialog(contentPanel,"Username cannot be empty!");
                return;
            }

            if(StringUtil.isEmpty(password)){
                JOptionPane.showMessageDialog(contentPanel,"Password cannot be empty!");
                return;
            }

            // login as admin
            Admin admin = null;
            if(userType == 0){
                AdminDao ad = new AdminDao();

                Admin tempAdmin = new Admin();
                tempAdmin.setUsername(username);
                tempAdmin.setPassword(password);

                admin = ad.login(tempAdmin);

                if(admin == null){
                    JOptionPane.showMessageDialog(contentPanel,"Username or password is wrong!");
                    return;
                }

                // pop message to show login successfully
                JOptionPane.showMessageDialog(contentPanel,"Welcome, Admin: "+admin.getName()+"!");
                // close login frame
                this.dispose();
                // create and move to main frame
                new MainFrame(userType,admin);
                System.out.println("Admin login successfully");
            }else{
                // login as student
            }
        });

        /*
         * brief: ActionListener to clear the entered username,
         * password, and reset the login type to admin.
         */
        reset_button.addActionListener(e -> {
                // clear user text field
                user_tf.setText("");
                // clear password field
                password_pf.setText("");
                // set combo box to the first item
                userType_cb.setSelectedIndex(0);

        });
    }

    public static void main(String[] args){
        LoginFrame myLog = new LoginFrame();
    }
}
