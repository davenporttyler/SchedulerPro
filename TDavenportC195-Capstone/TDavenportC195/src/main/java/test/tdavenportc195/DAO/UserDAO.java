package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UserDAO Class is for creating methods to query database
 *
 * @author Tyler Davenport
 */

public class UserDAO {


    ///-----------------------------Get All Users-----------------------------------------------------///

    /**
     *      Method to query the database and get all the User information
     * @return                  Returns Result Set to execute the query
     */

    public static ObservableList getAllUsers() {

        ObservableList<Users> allUsers = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT * FROM users";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                int user_ID = resSet.getInt("User_ID");
                String user_Name = resSet.getString("User_Name");
                String password = resSet.getString("Password");


                Users newUser = new Users(user_ID, user_Name, password);
                allUsers.add(newUser);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allUsers;
    }


    ///---------------------------------Check User-------------------------------------------------///

    /**
     *          Method to get all the users and filter the query by passing in a Username and password to check
     *          if they are a valid user
     * @param username          Username of the user selected
     * @param password          Password of the user selected
     * @return                  Boolean value based on user selected
     * @throws SQLException     Throws SQL Exception
     */

    public static boolean checkUser(String username, String password) throws SQLException {

        String sqlSelect = "SELECT * FROM Users WHERE User_Name = ? AND Password = ?";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);

        prepStatement.setString(1, username);
        prepStatement.setString(2, password);

        ResultSet resSet = prepStatement.executeQuery();

        if(resSet.next()) {
            Users users = new Users();
            users.setUser_Name(resSet.getString("User_Name"));
            users.setPassword(resSet.getString("Password"));

            return true;
        }

        System.out.println("Credentials don't match a User in database!");
        return false;
    }







}
