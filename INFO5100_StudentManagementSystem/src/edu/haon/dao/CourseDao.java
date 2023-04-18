package edu.haon.dao;

import edu.haon.model.Course;
import edu.haon.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends BaseDao{

    public int addCourse(Course course){
        int result = 0;

        String sql =
                "SELECT * FROM Courses WHERE CourseName = ?";
        ResultSet rs = null;
        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,course.getName());
            rs = prepState.executeQuery();

            if(!rs.next()){
                String sqlAdd = "INSERT INTO Courses VALUES(?,?,?)";
                try {
                    prepState = conn.prepareStatement(sqlAdd);
                    prepState.setString(1,course.getName());
                    prepState.setString(2,course.getInstructor());
                    prepState.setString(3,String.valueOf(course.getCapacity()));
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

    public int deleteCourse(String cour_name){
        int result = 0;
        String sql = "DELETE FROM Courses WHERE CourseName = ?";

        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,cour_name);
            result = prepState.executeUpdate();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return result;
    }

    public List<Course> getCourseList(Course course){
        List<Course> courseList = new ArrayList<Course>();
        String sql = "SELECT * FROM Courses";

        if(!StringUtil.isEmpty(course.getName())){
            sql += (" AND CourseName LIKE '%"+course.getName()+"%'");

        }

        if(!StringUtil.isEmpty(course.getInstructor())){
            sql += (" AND Instructor LIKE '%"+course.getInstructor()+"%'");
        }

        try {
            sql = sql.toString().replaceFirst("AND","WHERE");
            PreparedStatement prepState = conn.prepareStatement(sql);
            ResultSet rs = prepState.executeQuery();

            // add all found Student Object to list
            while (rs.next()){
                Course c = new Course();
                c.setCourseId(rs.getInt("CourseId"));
                c.setName(rs.getString("CourseName"));
                c.setInstructor(rs.getString("Instructor"));
                c.setCapacity(rs.getInt("Capacity"));
                courseList.add(c);
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return courseList;
    }

    public int editCourse(String course_id, String new_name, String new_ins,String new_cap){
        int result = 0;
        String sql = "UPDATE Courses";

        if(!StringUtil.isEmpty(new_name)){
            sql += " , CourseName = '"+new_name+"'";
        }

        if(!StringUtil.isEmpty(new_ins)){
            sql += " , Instructor = '"+new_ins+"'";
        }

        if(!StringUtil.isEmpty(new_cap)){
            sql += " , Capacity = '"+new_cap+"'";
        }

        sql += " WHERE CourseId = ?";

        try {
            sql = sql.toString().replaceFirst(",","SET");
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,course_id);
            result = prepState.executeUpdate();

        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        return result;
    }
}
