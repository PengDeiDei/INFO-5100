package edu.haon.view;

import edu.haon.dao.AdminDao;
import edu.haon.dao.CourseDao;
import edu.haon.dao.StudentDao;
import edu.haon.model.Admin;
import edu.haon.model.Course;
import edu.haon.model.Student;
import edu.haon.util.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AdminFrame extends JFrame{
    public Admin admin;
    private JPanel contentPanel;
    private JTabbedPane main_tp;
    private JPanel student_p;
    private JPanel course_p;
    private JPanel password_p;
    private JTabbedPane student_tp;
    private JPanel listStu_p;
    private JPanel addStu_p;
    private JTabbedPane course_tp;
    private JPanel addCourse_p;
    private JLabel curr_pw_lb;
    private JPasswordField curr_password_pf;
    private JLabel new_pw_lb;
    private JTextField new_password_tf;
    private JButton password_b1;
    private JButton password_b2;
    private JTextField confirm_pw_tf;
    private JLabel confirm_pw_lb;
    private JLabel stu_name_lb1;
    private JTextField stu_name_tf1;
    private JTextField stu_major_tf;
    private JLabel stu_major_lb;
    private JButton stu_add_b;
    private JButton stu_clear_b;
    private JTextField stu_name_tf2;
    private JButton stu_search_b;
    private JLabel stu_name_lb2;
    private JLabel stu_major_lb2;
    private JTextField stu_major_tf2;
    private JScrollPane stu_list_sp;
    private JTable stu_list_tbl;
    private JButton stu_delete_b;
    private JButton stu_edit_b;
    private JLabel stu_name_lb3;
    private JLabel stu_major_lb3;
    private JTextField stu_name_tf3;
    private JTextField stu_major_tf3;
    private JLabel coursename_lb1;
    private JLabel instructor_lb;
    private JTextField coursename_tf1;
    private JTextField instructor_tf1;
    private JLabel capacity_lb;
    private JTextField capacity_tf1;
    private JButton course_add_b;
    private JButton course_clear_b;
    private JPanel listCourse_p;
    private JButton course_search_b;
    private JTextField coursename_tf2;
    private JTextField instructor_tf2;
    private JTable course_list_tbl;
    private JLabel coursename_lb2;
    private JLabel instructor_lb2;
    private JButton course_delete_b;
    private JLabel coursename_lb3;
    private JTextField coursename_tf3;
    private JLabel instructor_lb3;
    private JTextField instructor_tf3;
    private JButton course_edit_b;
    private JScrollPane course_list_sp;
    private JLabel capacity_lb2;
    private JTextField capacity_tf2;

    public AdminFrame(Admin admin){
        this.admin = admin;

        setContentPane(contentPanel);
        setTitle("Student Management System");
        setSize(900,650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    // Personal Setting Panel:
        // Change Password button
        password_b1.addActionListener(e-> {
            String curr_pw = new String(curr_password_pf.getPassword());
            String new_pw = new_password_tf.getText();
            String confirm_pw = confirm_pw_tf.getText();

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

            ad.closeDao(); // close connection
        });

        // Clear password button
        password_b2.addActionListener(e->{
            curr_password_pf.setText("");
            new_password_tf.setText("");
            confirm_pw_tf.setText("");
        });


    // Student Management Panel:
        // Add Student Panel:
        // Add new student button
        stu_add_b.addActionListener(e->{
            String stu_name = stu_name_tf1.getText();
            String stu_major = stu_major_tf.getText();

            if(StringUtil.isEmpty(stu_name)){
                JOptionPane.showMessageDialog(contentPanel, "Please enter student name.");
                return;
            }
            if(StringUtil.isEmpty(stu_major)){
                JOptionPane.showMessageDialog(contentPanel, "Please enter student major.");
                return;
            }

            // generate student username and password
            String[] stu_result = getUserAndPassword(stu_name);

            // create new Student object
            Student student = new Student();
            student.setUsername(stu_result[0]); student.setPassword(stu_result[1]);
            student.setName(stu_name); student.setMajor(stu_major);

            StudentDao sd = new StudentDao();
            int result = sd.addStudent(student);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel, "Add new student successfully!\n" +
                        "Username: "+stu_result[0]+"\nPassword: "+stu_result[1]);
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to add this student!");
            }
        });

        // Clear student information button
        stu_clear_b.addActionListener(e->{
            stu_name_tf1.setText("");
            stu_major_tf.setText("");
        });

        // Student List Panel:
        // list students by searching name and major
        stu_search_b.addActionListener(e -> {
            String stu_name = stu_name_tf2.getText();
            String stu_major = stu_major_tf2.getText();

            Student stu = new Student();
            stu.setName(stu_name);
            stu.setMajor(stu_major);

            setTable(stu);
        });

        // delete student by selected row
        stu_delete_b.addActionListener(e->{
            int row = stu_list_tbl.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(contentPanel,"Please select the row to delete.");
                return;
            }

            String name = stu_list_tbl.getValueAt(row,1).toString();

            StudentDao sd = new StudentDao();
            int result = sd.deleteStudent(name);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel,"Delete successfully.");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to delete.");
            }

            // refresh table no matter the student is deleted or not
            setTable(new Student());
        });

        // edit information of selected student
        stu_edit_b.addActionListener(e-> {
            int row = stu_list_tbl.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(contentPanel,"Please select the row to edit.");
                return;
            }

            // get studentId, new name and new major
            String stu_id = stu_list_tbl.getValueAt(row,0).toString();
            String new_name = stu_name_tf3.getText();
            String new_major = stu_major_tf3.getText();

            // if both text fields are empty, pop message to ask user for inputs
            if(StringUtil.isEmpty(new_name) && StringUtil.isEmpty(new_major)){
                JOptionPane.showMessageDialog(contentPanel,"Please enter the information you want edit.");
                return;
            }

            StudentDao sd = new StudentDao();
            int result = sd.editStudent(stu_id, new_name, new_major);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel,"Edit successfully.");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to edit.");
            }

            // refresh table no matter the student is edited or not
            setTable(new Student());
        });

    // Course Management Panel:
        // Add New Course Panel:
        // Add button to add new course
        course_add_b.addActionListener(e-> {
            String c_name = coursename_tf1.getText();
            String c_ins = instructor_tf1.getText();
            String c_cap = capacity_tf1.getText();

            if(StringUtil.isEmpty(c_name)){
                JOptionPane.showMessageDialog(contentPanel, "Please enter course name.");
                return;
            }
            if(StringUtil.isEmpty(c_ins)){
                JOptionPane.showMessageDialog(contentPanel, "Please enter course instructor.");
                return;
            }
            if(StringUtil.isEmpty(c_cap)){
                JOptionPane.showMessageDialog(contentPanel, "Please enter course capacity.");
                return;
            }

            // create new Course object
            Course course  = new Course();
            course.setName(c_name); course.setInstructor(c_ins);
            course.setCapacity(Integer.parseInt(c_cap));

            CourseDao cd = new CourseDao();
            int result = cd.addCourse(course);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel, "Add new course successfully!");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to add this course!");
            }
        });

        // Clear button to clear entered information
        course_clear_b.addActionListener(e-> {
            coursename_tf1.setText("");
            instructor_tf1.setText("");
            capacity_tf1.setText("");
        });

        // Course List Panel:
        // Search button to search course by course name and instructor
        course_search_b.addActionListener(e->{
            String course_name = coursename_tf2.getText();
            String instructor = instructor_tf2.getText();

            Course c = new Course();
            c.setName(course_name);
            c.setInstructor(instructor);

            setTable(c);
        });

        // Delete button to delete selected course
        course_delete_b.addActionListener(e->{
            int row = course_list_tbl.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(contentPanel,"Please select the row to delete.");
                return;
            }

            String name = course_list_tbl.getValueAt(row,1).toString();

            CourseDao cd = new CourseDao();
            int result = cd.deleteCourse(name);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel,"Delete successfully.");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to delete.");
            }

            // refresh table no matter the student is deleted or not
            setTable(new Course());
        });

        // Edit button to edit selected course
        course_edit_b.addActionListener(e->{
            int row = course_list_tbl.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(contentPanel,"Please select the row to delete.");
                return;
            }

            // get courseId, new name and new instructor
            String course_id = course_list_tbl.getValueAt(row,0).toString();
            String new_name = coursename_tf3.getText();
            String new_ins = instructor_tf3.getText();
            String new_cap = capacity_tf2.getText();

            // if both text fields are empty, pop message to ask user for inputs
            if(StringUtil.isEmpty(new_name) && StringUtil.isEmpty(new_ins) && StringUtil.isEmpty(new_cap)){
                JOptionPane.showMessageDialog(contentPanel,"Please enter the information you want edit.");
                return;
            }

            CourseDao cd = new CourseDao();
            int result = cd.editCourse(course_id, new_name, new_ins,new_cap);

            if(result >0){
                JOptionPane.showMessageDialog(contentPanel,"Edit successfully.");
            }else{
                JOptionPane.showMessageDialog(contentPanel,"Unable to edit.");
            }

            // refresh table no matter the student is edited or not
            setTable(new Course());
        });
    }

    /*
     * @brief: method to generate username and password for new student
     *  by taking student name
     *
     * @param: stu_name String
     *
     * @return: result String[]
     */
    private String[] getUserAndPassword(String stu_name){
        String[] temp_name = stu_name.split(" ");
        String name_sc = (temp_name[0].substring(0,2)+temp_name[1].charAt(0)).toLowerCase();

        String[] result = new String[2];
        result[0] = "student."+ name_sc;
        result[1] = name_sc.toUpperCase().charAt(0) +""+name_sc.charAt(2)+"111";

        return result;
    }

    /*
     * @brief: set student list table by taking the input Student object
     *  search the student name and major from the input object,
     *  return found data stored in the list of Student objects,
     *  create table with the returned list
     *
     * @param: student Student Object
     */
    private void setTable(Student student){
        StudentDao sd = new StudentDao();
        List<Student> studentList = sd.getStudentList(student);

        DefaultTableModel dtm = new DefaultTableModel(null, new String[]{"Student Id","Student Name","Major", "Username","Password"});
        stu_list_tbl.setModel(dtm);

        for(Student s: studentList){
            Vector v = new Vector();
            v.add(s.getStudentId());
            v.add(s.getName());
            v.add(s.getMajor());
            v.add(s.getUsername());
            v.add(s.getPassword());
            dtm.addRow(v);
        }

        sd.closeDao();
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
