package edu.haon.view;

import javax.swing.*;

public class MainFrame extends JFrame{
    private int userType;
    private Object userObj;
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
    private JButton pw_b2;
    private JTextField textField1;
    private JLabel repeat_pw_lb;

    public MainFrame(int userType, Object userObj){
        // get userType and userObject
        this.userType = userType;
        this.userObj = userObj;

        setContentPane(contentPanel);
        setTitle("Student Management System");
        setSize(900,650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
