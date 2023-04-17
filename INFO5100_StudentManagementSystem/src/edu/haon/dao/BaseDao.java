package edu.haon.dao;

import edu.haon.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {
    // Create a public Connection object for interactions with SQL Server Database
    public Connection conn = new DBUtil().getConn();

    public void closeDao(){
        try{
            conn.close();
        } catch (
                SQLException sqlE){
            sqlE.printStackTrace();
        }
    }
}
