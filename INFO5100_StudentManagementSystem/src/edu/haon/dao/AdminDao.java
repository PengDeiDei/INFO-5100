package edu.haon.dao;

import edu.haon.model.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try{
            conn.close();
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        return adminRs;
    }
}
