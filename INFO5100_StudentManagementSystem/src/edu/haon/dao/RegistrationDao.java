package edu.haon.dao;

import edu.haon.model.Course;
import edu.haon.model.Registration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDao extends BaseDao{

    public int enrollCourse(Registration enroll){
        int result = 0;

        String sql =
                "SELECT * FROM Registrations WHERE CourseId = ?";
        ResultSet rs = null;
        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setInt(1,enroll.getCourseId());
            rs = prepState.executeQuery();

            if(!rs.next()){
                String sqlAdd = "INSERT INTO Registrations VALUES(?,?)";
                try {
                    prepState = conn.prepareStatement(sqlAdd);
                    prepState.setInt(1,enroll.getCourseId());
                    prepState.setInt(2,enroll.getStudentId());
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

    public int withdrawCourse(Registration enroll){
        int result = 0;
        String sqlAdd = "DELETE FROM Registrations WHERE CourseId = ?" +
                        " AND StudentId = ?";
        try {
            PreparedStatement prepState = conn.prepareStatement(sqlAdd);
            prepState.setInt(1,enroll.getCourseId());
            prepState.setInt(2,enroll.getStudentId());
            result = prepState.executeUpdate();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return result;
    }

    public List<Course> getMyCourses(int studentId){
        List<Course> myCourseList = new ArrayList<Course>();

        String sql = "SELECT r.CourseId, c.CourseName, c.Instructor, c.Capacity " +
                "FROM Registrations r " +
                "JOIN Courses c ON r.CourseId = c.CourseId " +
                "WHERE r.StudentId = ? ";

        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setInt(1,studentId);
            ResultSet rs = prepState.executeQuery();

            // add all found Student Object to list
            while (rs.next()){
                Course c = new Course();
                c.setCourseId(rs.getInt("CourseId"));
                c.setName(rs.getString("CourseName"));
                c.setInstructor(rs.getString("Instructor"));
                c.setCapacity(rs.getInt("Capacity"));
                myCourseList.add(c);
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return myCourseList;
    }
}
