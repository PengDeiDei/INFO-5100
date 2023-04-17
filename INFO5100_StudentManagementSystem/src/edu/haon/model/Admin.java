package edu.haon.model;

/*
 * @brief: Admin data model to hold information of Admins,
 * including ID, last name, first name, and password
 */

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String name;

    /*
     * @brief: get adminId
     *
     * @return: adminId int
     */
    public int getAdminId(){
        return adminId;
    }

    /*
     * @brief: set admin id
     *
     * @param: adminId int
     */
    public void setAdminId(int adminId){
        this.adminId = adminId;
    }

    /*
     * @brief: get username
     *
     * @return: username String
     */
    public String getUsername(){
        return username;
    }

    /*
     * @brief: set username
     *
     * @param: username String
     */
    public void setUsername(String username){
        this.username = username;
    }

    /*
     * @brief: get admin password
     *
     * @return: password String
     */
    public String getPassword(){
        return password;
    }

    /*
     * @brief: set admin password
     *
     * @param: password String
     */
    public void setPassword(String password){
        this.password = password;
    }

    /*
     * @brief: set admin name
     *
     * @param: name String
     */
    public void setName(String name){
        this.name = name;
    }

    /*
     * @brief: get admin name
     *
     * @return: name String
     */
    public String getName(){
        return name;
    }
}
