package edu.haon.view;

import edu.haon.dao.CourseDao;
import edu.haon.dao.RegistrationDao;
import edu.haon.dao.StudentDao;
import edu.haon.model.Course;
import edu.haon.model.Registration;
import edu.haon.model.Student;
import edu.haon.util.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class StudentFrame extends JFrame{

    public Student student;
    private JPanel contentPanel;
    private JTabbedPane main_tp;
    private JPanel self_p;
    private JTabbedPane self_tp;
    private JPanel pw_p;
    private JLabel curr_pw_lb;
    private JLabel new_pw_lb;
    private JTextField new_password_tf;
    private JPasswordField curr_password_pf;
    private JButton password_b1;
    private JLabel confirm_pw_lb;
    private JTextField confirm_pw_tf;
    private JButton password_b2;
    private JPanel course_p;
    private JTabbedPane course_tp;
    private JPanel listCourse_p;
    private JLabel coursename_lb;
    private JTextField coursename_tf;
    private JLabel instructor_lb;
    private JTextField instructor_tf;
    private JButton enroll_b;
    private JButton course_search_b;
    private JButton withdraw_b;
    private JScrollPane course_list_sp;
    private JTable course_list_tbl;
    private JPanel my_course_p;
    private JScrollPane my_course_sp;
    private JTable my_course_tbl;
    private JButton refresh_b;

    public StudentFrame(Student student) {
        this.student = student;

        setContentPane(contentPanel);
        setTitle("Student Management System");
        setSize(900, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    // Personal Setting Panel:
        // Change Password button
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
            if(!curr_pw.equals(student.getPassword())){
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

            StudentDao sd = new StudentDao();

            int result = sd.editPassword(student,new_pw);

            if(result >0){
                student.setPassword(new_pw);
                JOptionPane.showMessageDialog(contentPanel,"Password changed successfully!");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to change your password!");
            }

            sd.closeDao(); // close connection
        });

        // clear button
        password_b2.addActionListener(e-> {
            curr_password_pf.setText("");
            new_password_tf.setText("");
            confirm_pw_tf.setText("");
        });

    // Course Selection Panel:
        // Course List Panel:
        // Search button to search course by name and instructor
        course_search_b.addActionListener(e->{
            String course_name = coursename_tf.getText();
            String instructor = instructor_tf.getText();

            Course c = new Course();
            c.setName(course_name);
            c.setInstructor(instructor);

            setTable(c);
        });

        // Enroll button to enroll selected course
        enroll_b.addActionListener(e->{
            int row = course_list_tbl.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(contentPanel,"Please select the course to enroll.");
                return;
            }

            int course_id = (int) course_list_tbl.getValueAt(row,0);
            Registration enroll = new Registration();
            enroll.setCourseId(course_id);
            enroll.setStudentId(student.getStudentId());

            RegistrationDao rd = new RegistrationDao();
            int result = rd.enrollCourse(enroll);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel,"Enroll successfully.");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to enroll.");
            }
        });

        // My Courses Panel:
        // Refresh button to get list of enrolled courses
        refresh_b.addActionListener(e->{
            setTable();
        });

        // Withdraw button to withdraw selected course
        withdraw_b.addActionListener(e -> {
            int row = my_course_tbl.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(contentPanel,"Please select the course to enroll.");
                return;
            }

            int course_id = (int) my_course_tbl.getValueAt(row,0);
            Registration enroll = new Registration();
            enroll.setCourseId(course_id);
            enroll.setStudentId(student.getStudentId());

            RegistrationDao rd = new RegistrationDao();
            int result = rd.withdrawCourse(enroll);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel,"Withdraw successfully.");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to withdraw.");
            }

            setTable();
        });
    }

    private void setTable(){
        List<Course> myList = null;
        RegistrationDao rd = new RegistrationDao();
        myList = rd.getMyCourses(student.getStudentId());

        DefaultTableModel dtm = new DefaultTableModel(null, new String[]{"Course Id","Course Name","Instructor","Capacity"});
        my_course_tbl.setModel(dtm);

        for(Course c: myList){
            Vector v = new Vector();
            v.add(c.getCourseId());
            v.add(c.getName());
            v.add(c.getInstructor());
            v.add(c.getCapacity());
            dtm.addRow(v);
        }

        rd.closeDao();
    }

    private void setTable(Course course){
        CourseDao cd = new CourseDao();
        List<Course> courseList = cd.getCourseList(course);

        DefaultTableModel dtm = new DefaultTableModel(null, new String[]{"Course Id","Course Name","Instructor", "Capacity"});
        course_list_tbl.setModel(dtm);

        for(Course c: courseList){
            Vector v = new Vector();
            v.add(c.getCourseId());
            v.add(c.getName());
            v.add(c.getInstructor());
            v.add(c.getCapacity());
            dtm.addRow(v);
        }

        cd.closeDao();
    }
}
