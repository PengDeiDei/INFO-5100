package edu.haon.dao;

import edu.haon.model.Admin;
import edu.haon.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDao extends BaseDao{

    /*
     * @brief: admin login method; taking the input Admin object,
     * execute the SQL query command to get related information
     * from database, and return the found Admin object
     *
     * @param: admin Admin
     *
     * @return: adminRs Admin, if found
     *          null, otherwise
     */
    public Admin login(Admin admin){
        String sql =
                "SELECT * FROM Admins WHERE AdminUsername = ? AND AdminPassword = ?";

        Admin adminRs = null;

        // try passing SQL command to database
        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,admin.getUsername());
            prepState.setString(2,admin.getPassword());
            ResultSet rs = prepState.executeQuery();

            if(rs.next()){
                adminRs = new Admin();
                adminRs.setAdminId(rs.getInt("AdminId"));
                adminRs.setUsername(rs.getString("AdminUsername"));
                adminRs.setPassword(rs.getString("AdminPassword"));
                adminRs.setName(rs.getString("AdminName"));
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        // try closing the connection to database
        this.closeDao();

        return adminRs;
    }

    /*
     * @brief: method to update admin password to SQL database
     *
     * @ param: admin Admin Object,
     *          new_pw String
     *
     * @return: result int
     */
    public int editPassword(Admin admin, String new_pw){
        int result = 0;
        String sql = "UPDATE Admins SET AdminPassword = ? WHERE AdminUsername = ?";

        try {
            PreparedStatement prepState = conn.prepareStatement(sql);
            prepState.setString(1,new_pw);
            prepState.setString(2,admin.getUsername());
            result = prepState.executeUpdate();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }

        return result;
    }
}
