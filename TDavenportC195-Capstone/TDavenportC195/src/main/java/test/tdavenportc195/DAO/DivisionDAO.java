package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Customers;
import test.tdavenportc195.Models.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The DivisionDAO Class is for creating methods to query database
 *
 * @author Tyler Davenport
 */

public class DivisionDAO {

    ///-------------------------------Get All Divisions---------------------------------------------------///

    /**
     *      Method to query the database to get all the Division information
     * @return      ObservableList allDivisions
     */

    public static ObservableList<Division> getAllDivisions() {

        ObservableList<Division> allDivisions = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT * FROM first_level_divisions";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                int division_ID = resSet.getInt("Division_ID");
                String division = resSet.getString("Division");
                int country_ID = resSet.getInt("Country_ID");

                Division newDivision = new Division(division_ID, division, country_ID);
                allDivisions.add(newDivision);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allDivisions;

    }










}
