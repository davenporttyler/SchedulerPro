package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The CountryDAO Class is for creating methods to query database
 *
 * @author Tyler Davenport
 */

public class CountryDAO {


    ///-----------------------------Get All Countries-----------------------------------------------------///

    /**
     *      Method to get all Countries from database
     * @return      ObservableList allCountrys
     */

    public static ObservableList<Country> getAllCountrys() {

        ObservableList<Country> allCountrys = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT * FROM countries";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                int country_ID = resSet.getInt("Country_ID");
                String country = resSet.getString("Country");

                Country newCountry = new Country(country_ID, country);
                allCountrys.add(newCountry);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allCountrys;

    }

    ///-----------------------------------------------------------------------------------------///

    /**
     *      Method to get only Country Names from the countries table
     * @return      ObservableList getCountry
     */

    public static ObservableList<Country> getJustCountrys() {

        ObservableList<Country> getCountry = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT Country FROM countries";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();


            while (resSet.next()) {

                String country = resSet.getString("Country");

                Country newGetCountry = new Country(country);
                getCountry.add(newGetCountry);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return getCountry;

    }





}
