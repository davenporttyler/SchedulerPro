package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Appointments;
import test.tdavenportc195.Models.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * The ContactDAO Class is for creating methods to query database
 *
 * @author Tyler Davenport
 */

public class ContactDAO {


    ///--------------------------------Get All Contacts--------------------------------------------------///

    /**
     *      Method to get all Contacts from database
     * @return      ObservableList allContacts
     */

    public static ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

        try {

            //sql Select Statement
            String sqlSelect = "SELECT * FROM contacts";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                int contact_ID = resSet.getInt("Contact_ID");
                String contact_Name = resSet.getString("Contact_Name");
                String email = resSet.getString("Email");

                Contacts newContact = new Contacts(contact_ID, contact_Name, email);
                allContacts.add(newContact);
            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allContacts;
    }





}
