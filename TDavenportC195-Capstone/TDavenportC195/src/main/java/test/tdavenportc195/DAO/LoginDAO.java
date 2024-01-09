package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The LoginDAO Class is for creating methods to query database
 *
 * @author Tyler Davenport
 */


public class LoginDAO {

    private static int UserID;
    private static String UserName;
    private static String Password;


    ///------------------------------Get All Users----------------------------------------------------///

    /**
     *      Method to query the database and get all of the User information
     * @throws SQLException     Throws and SQL Exception
     */

    public static void getAllUsers() throws SQLException {

        ObservableList<Users> availableUsers = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM users";

            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sql);

            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()){

                UserID = resSet.getInt("User_ID");
                UserName = resSet.getString("User_Name");
                Password = resSet.getString("Password");

                Users loginUsers = new Users(UserID, UserName, Password);

                availableUsers.add(loginUsers);

            }
        }
        catch (SQLException e){

        }

    }



}
