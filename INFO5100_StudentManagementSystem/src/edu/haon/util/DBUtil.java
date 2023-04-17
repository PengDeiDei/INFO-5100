package edu.haon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private String dbURL = "jdbc:sqlserver://boyce.coe.neu.edu;database=HaonanPeng_INFO;"+"encrypt=true;trustServerCertificate=true";
    private String dbUserName = "INFO6210";
    private String dbPassword = "NEUHusky!";
    private String jdbcName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /*
     * @brief: method to connect to SQL server
     *
     * @return: conn Connection
     *
     * @throws Exception
     */
    public Connection getConn(){
        // try to load JDBC SQLServer driver
        try{
            Class.forName(jdbcName);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Connection conn = null;
        try{
            conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /*
     * @brief: method to close the connection to SQL server
     *
     * @throws Exception
     */
    public void closeConn(Connection conn) throws Exception{
        if(conn != null){
            conn.close();
        }
    }
}
