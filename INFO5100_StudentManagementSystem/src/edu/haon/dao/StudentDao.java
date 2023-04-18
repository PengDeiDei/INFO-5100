package edu.haon.dao;

import edu.haon.model.Student;
import edu.haon.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends BaseDao{
    /*
     *  @brief: admin login method; taking the input Student object,
     *  execute the SQL query command to get related information
     *  from database, and return the found Student object
     *
     *  @param: student Student Object
     *
     *  @return: studentRs Student, if found
     *          null, otherwise
     */
    public Student login(Student student){
        String sql =
                "SELECT * FROM Students WHERE StudentUsername = ? AND StudentPassword = ?";

        Student studentRs = null;

        // try passing SQL command to database
        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,student.getUsername());
            prepState.setString(2,student.getPassword());
            ResultSet rs = prepState.executeQuery();

            if(rs.next()){
                studentRs = new Student();
                studentRs.setStudentId(rs.getInt("StudentId"));
                studentRs.setUsername(rs.getString("StudentUsername"));
                studentRs.setPassword(rs.getString("StudentPassword"));
                studentRs.setName(rs.getString("StudentName"));
                studentRs.setMajor(rs.getString("StudentMajor"));
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        // try closing the connection to database
        try{
            conn.close();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        return studentRs;
    }

    /*
     *  @brief: method to update student password to SQL database
     *
     *  @param: student Student Object,
     *          new_pw String
     *
     *  @return: result int
     */
    public int editPassword(Student student, String new_pw){
        int result = 0;
        String sql = "UPDATE Students SET StudentPassword = ? WHERE StudentUsername = ?";

        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,new_pw);
            prepState.setString(2,student.getUsername());
            result = prepState.executeUpdate();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        try{
            conn.close();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return result;
    }

    /*
     *  @brief: method to add student to database by taking
     *  the input Student object, return the result of insertion
     *
     *  @param: student Student
     *
     *  @return: result int
     */
    public int addStudent(Student student){
        int result = 0;

        String sql =
                "SELECT * FROM Students WHERE StudentUsername = ?";
        ResultSet rs = null;
        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,student.getUsername());
            rs = prepState.executeQuery();

            if(!rs.next()){
                String sqlAdd = "INSERT INTO Students VALUES(?,?,?,?)";
                try {
                    prepState = conn.prepareStatement(sqlAdd);
                    prepState.setString(1,student.getUsername());
                    prepState.setString(2,student.getPassword());
                    prepState.setString(3,student.getName());
                    prepState.setString(4,student.getMajor());
                    result = prepState.executeUpdate();
                } catch (SQLException sqlE){
                    sqlE.printStackTrace();
                }
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return result;
    }

    public int deleteStudent(String stu_name){
        int result = 0;
        String sql = "DELETE FROM Students WHERE StudentName = ?";

        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,stu_name);
            result = prepState.executeUpdate();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return result;
    }

    public List<Student> getStudentList(Student student){
        List<Student> stuList = new ArrayList<Student>();
        String sql = "SELECT * FROM Students";

        if(!StringUtil.isEmpty(student.getName())){
            sql += (" AND StudentName LIKE '%"+student.getName()+"%'");

        }

        if(!StringUtil.isEmpty(student.getMajor())){
            sql += (" AND StudentMajor = '"+student.getMajor()+"'");
        }

        try {
            sql = sql.toString().replaceFirst("AND","WHERE");
            PreparedStatement prepState = conn.prepareStatement(sql);
            ResultSet rs = prepState.executeQuery();

            // add all found Student Object to list
            while (rs.next()){
                Student s = new Student();
                s.setStudentId(rs.getInt("StudentId"));
                s.setUsername(rs.getString("StudentUsername"));
                s.setPassword(rs.getString("StudentPassword"));
                s.setName(rs.getString("StudentName"));
                s.setMajor(rs.getString("StudentMajor"));
                stuList.add(s);
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return stuList;
    }

    /*
     *  @brief: method to change student information by taking
     *  the student id, new name, and new major; return the
     *  integer result to indicate whether the update is successes
     *  or not.
     *
     *  @param: stu_id String,
     *          new_name String,
     *          new_major String
     *
     * @return: result int
     */
    public int editStudent(String stu_id, String new_name, String new_major){
        int result = 0;
        String sql = "UPDATE Students";
        if(!StringUtil.isEmpty(new_name)){
            sql += " , StudentName = '"+new_name+"'";
        }

        if(!StringUtil.isEmpty(new_major)){
            sql += " , StudentMajor = '"+new_major+"'";
        }

        sql += " WHERE StudentId = ?";

        try {
            sql = sql.toString().replaceFirst(",","SET");
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,stu_id);
            result = prepState.executeUpdate();

        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        return result;
    }
}
