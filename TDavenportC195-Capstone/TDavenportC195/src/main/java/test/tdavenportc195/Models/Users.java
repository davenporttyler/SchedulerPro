package test.tdavenportc195.Models;

import java.time.LocalDateTime;

/**
 *  User Model Class
 *
 * @author Tyler Davenport
 */
public class Users {

    private int User_ID;
    private String User_Name;
    private String Password;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;

    ///-----------------Empty Users Constructor-----------------------------------------------------------------///

    /**
     *      Empty User Constructor
     */
    public Users() {}

    ///----------------------Users Constructor------------------------------------------------------------///

    /**
     *      User Constructor
     * @param User_ID       The ID of the user
     * @param User_Name     The Name of the user
     * @param Password      The Password of the user
     */

    public Users(int User_ID, String User_Name, String Password){

        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;

    }

    ///---------------------Getters & Setters-------------------------------------------------------------///

    /**
     *      Getter for User ID
     * @return  User ID
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     *      Setter for User ID
     * @param user_ID   User ID to be set
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     *      Getter for User Name
     * @return  User Name
     */
    public String getUser_Name() {
        return User_Name;
    }

    /**
     *      Setter for User Name
     * @param user_Name     User Name to be set
     */
    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    /**
     *      Getter for User Password
     * @return  User Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     *      Setter for User Password
     * @param password      User Password to be set
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     *      Getter for Create Date
     * @return  Create Date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     *      Setter for Create Date
     * @param create_Date       Create Date to be set
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     *      Getter for Created By
     * @return  Created By
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     *      Setter for Created By
     * @param created_By        Created By to be set
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     *      Getter for Last Update
     * @return  Last Update
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     *      Setter for Last Update
     * @param last_Update       Last Update to be set
     */
    public void setLast_Update(LocalDateTime last_Update) {
        Last_Update = last_Update;
    }

    /**
     *      Getter for Last updated by
     * @return  Last Updated By
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     *      Setter for last updated by
     * @param last_Updated_By   Last updated by to be set
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     *      Sets User Name to string
     * @return      String User Name
     */
    public String toString(){
        return User_Name;
    }
}
