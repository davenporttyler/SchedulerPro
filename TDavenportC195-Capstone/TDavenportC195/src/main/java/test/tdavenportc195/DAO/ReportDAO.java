package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Customers;
import test.tdavenportc195.Models.Reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * The ReportDAO Class is for creating methods to query database
 *
 * @author Tyler Davenport
 */


public class ReportDAO {


    /**
     *      Method to create Report 1: A list of all appointments grouped by Appointment Type, and counted by month
     * @return      ObservableList of Appointment Report items
     */
    public static ObservableList<Reports> getReport1() {

        ObservableList<Reports> report1 = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT Type AS 'Type', MONTHNAME(Start) AS 'Month Name', count(*) AS '# of Appointments'\n" +
                    "FROM appointments\n" +
                    "group by Type\n" +
                    "ORDER BY Start;";
            //Prepared Statement
            //System.out.println(sqlSelect);
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {


                String type = resSet.getString("Type");
                String monthName = resSet.getString("Month Name");
                int count = resSet.getInt("# of Appointments");


                Reports newReport = new Reports(type,monthName, count);
                report1.add(newReport);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return report1;

    }

    /**
     *      Method to create Report 2: A schedule for each Contact, ordered by the Contact's Name with times and other info needed
     * @return      ObservableList of Appointment Report items
     */
    public static ObservableList<Reports> getReport2() {

        ObservableList<Reports> report2 = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT Atab.Contact_ID, Atab.Contact_Name, BTab.Appointment_ID, Btab.Title, BTab.Type, Btab.Description, \n" +
                    "Btab.Start, Btab.End, Ctab.Customer_ID, Ctab.Customer_Name\n" +
                    "FROM contacts Atab, appointments Btab, customers Ctab\n" +
                    "WHERE Atab.Contact_ID = Btab.Contact_ID\n" +
                    "AND Btab.Customer_ID = Ctab.Customer_ID\n" +
                    "ORDER BY Atab.Contact_ID, Atab.Contact_Name, Btab.Start;";
            //Prepared Statement
            //System.out.println(sqlSelect);
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                String contact_ID = resSet.getString("Contact_ID");
                String contact_Name = resSet.getString("Contact_Name");
                int appointment_ID = resSet.getInt("Appointment_ID");
                String title = resSet.getString("Title");
                String type = resSet.getString("Type");
                String description = resSet.getString("Description");
                LocalDateTime start = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resSet.getTimestamp("End").toLocalDateTime();
                int customer_ID = resSet.getInt("Customer_ID");
                String customer_Name = resSet.getString("Customer_Name");

                Reports newReport = new Reports(contact_ID, contact_Name, appointment_ID, title, type, description, start, end, customer_ID, customer_Name);
                report2.add(newReport);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return report2;

    }

    /**
     *      Method to create Report 3: A list of all the Countries and Divisions the company services grouped by the Division, and counted per Division
     * @return      ObservableList of Division Report items
     */
    public static ObservableList<Reports> getReport3() {

        ObservableList<Reports> report3 = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT Atab.Country,  Btab.Division, count(Ctab.Customer_ID) AS '# of Customers'\n" +
                    "FROM countries Atab, first_level_divisions Btab, customers Ctab\n" +
                    "WHERE Atab.Country_ID = Btab.Country_ID\n" +
                    "AND Btab.Division_ID = Ctab.Division_ID\n" +
                    "GROUP BY  Btab.Division, Atab.Country\n" +
                    "ORDER BY Atab.Country_ID, Btab.Division;";
            //Prepared Statement
            //System.out.println(sqlSelect);
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                int customerCount = resSet.getInt("# of Customers");
                String country = resSet.getString("Country");
                String division = resSet.getString("Division");

                Reports newReport = new Reports(customerCount, country, division);
                report3.add(newReport);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return report3;

    }







}
