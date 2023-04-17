package edu.haon.view;

import edu.haon.dao.AdminDao;
import edu.haon.model.Admin;
import edu.haon.util.StringUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame{
    public Admin admin;
    private JPanel contentPanel;
    private JTabbedPane main_tp;
    private JPanel student_p;
    private JPanel course_p;
    private JPanel self_p;
    private JTabbedPane self_tp;
    private JPanel profile_p;
    private JPanel password_p;
    private JTabbedPane student_tp;
    private JPanel listStu_p;
    private JPanel addStu_p;
    private JPanel editStu_p;
    private JTabbedPane course_tp;
    private JPanel listCourse_p;
    private JPanel addCourse_p;
    private JPanel editCourse_p;
    private JLabel curr_pw_lb;
    private JPasswordField curr_password_pf;
    private JLabel new_pw_lb;
    private JTextField new_password_tf;
    private JButton password_b1;
    private JButton password_b2;
    private JTextField confirm_pw_tf;
    private JLabel confirm_pw_lb;

    public AdminFrame(Admin admin){
        this.admin = admin;

        setContentPane(contentPanel);
        setTitle("Student Management System");
        setSize(900,650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        password_b1.addActionListener(e-> {
            String curr_pw = new String(curr_password_pf.getPassword());
            String new_pw = new_password_tf.getText().toString();
            String confirm_pw = confirm_pw_tf.getText().toString();

            // pop message if current password is not entered
            if(StringUtil.isEmpty(curr_pw)){
                JOptionPane.showMessageDialog(contentPanel,"Please enter your current password.");
                return;
            }

            // pop message if current password is wrong
            // reset the text field
            if(!curr_pw.equals(admin.getPassword())){
                JOptionPane.showMessageDialog(contentPanel,"Current password is not correct!");
                curr_password_pf.setText("");
                return;
            }
            // pop message if new password is not entered
            if(StringUtil.isEmpty(new_pw)){
                JOptionPane.showMessageDialog(contentPanel,"Please enter your new password.");
                return;
            }
            // pop message if new password is not entered twice
            if(StringUtil.isEmpty(confirm_pw)){
                JOptionPane.showMessageDialog(contentPanel,"Please enter your new password again.");
                return;
            }
            // pop message if two new entered passwords are not the same
            // reset the confirmed password text field
            if(!new_pw.equals(confirm_pw)){
                JOptionPane.showMessageDialog(contentPanel,"The passwords you entered are not the same.\n"+
                        "Please enter again.");
                confirm_pw_tf.setText("");
                return;
            }

            AdminDao ad = new AdminDao();

            int result = ad.editPassword(admin,new_pw);

            if(result >0){
                admin.setPassword(new_pw);
                JOptionPane.showMessageDialog(contentPanel,"Password changed successfully!");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to change your password!");
            }
        });

        password_b2.addActionListener(e->{
            curr_password_pf.setText("");
            new_password_tf.setText("");
            confirm_pw_tf.setText("");
        });
    }

}
