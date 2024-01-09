package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Country;
import test.tdavenportc195.Models.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *      The CustomerDAO Class is for creating methods to query database
 *
 * @author Tyler Davenport
 */


public class CustomerDAO {

    ///--------------------------------Get All Customers + Join--------------------------------------------------///

    /**
     *      Method to query the database and get All Customers and country name, division name, and division_id
     * @return      ObservableList allCustomers (All Customers and Divisions and Country)
     */

    public static ObservableList<Customers> getAllCustomers() {

        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT Atab.Customer_ID, Atab.Customer_Name, Atab.Address, Atab.Postal_Code, Atab.Phone, Atab.Division_ID,\n" +
                    "Btab.Division, Ctab.Country \n"+
                    "FROM customers Atab, first_level_divisions Btab, countries Ctab\n" +
                    "WHERE Atab.Division_ID = Btab.Division_ID\n" +
                    "AND Btab.Country_ID = Ctab.Country_ID;";
            //Prepared Statement
            //System.out.println(sqlSelect);
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                int customer_ID = resSet.getInt("Customer_ID");
                String customer_Name = resSet.getString("Customer_Name");
                String address = resSet.getString("Address");
                String postal_Code = resSet.getString("Postal_Code");
                String phone = resSet.getString("Phone");
                String division = resSet.getString("Division");
                int division_ID = resSet.getInt("Division_ID");
                String country = resSet.getString("Country");

                Customers newCustomer = new Customers(customer_ID, customer_Name, address, postal_Code,
                        phone, division_ID, division, country);
                allCustomers.add(newCustomer);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allCustomers;

    }

    ///-------------------------------------Get All Customers----------------------------------------------------------------///

    /**
     *      Method to query the database and get All Customers
     * @return      ObservableList allCustomers (All Customers only)
     */

    public static ObservableList<Customers> getCustomers() {

        ObservableList<Customers> getCustomers = FXCollections.observableArrayList();

        try {
            //sql Select Statement
            String sqlSelect = "SELECT * FROM customers ";
            //Prepared Statement
            //System.out.println(sqlSelect);
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {

                int customer_ID = resSet.getInt("Customer_ID");
                String customer_Name = resSet.getString("Customer_Name");
                String address = resSet.getString("Address");
                String postal_Code = resSet.getString("Postal_Code");
                String phone = resSet.getString("Phone");

                Customers newGetCustomer = new Customers(customer_ID, customer_Name, address, postal_Code,
                        phone);
                getCustomers.add(newGetCustomer);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return getCustomers;

    }



    ///----------------------------Add New Customer------------------------------------------------------///

    /**
     *          Method to Add a customer to the database
     * @param customer_ID       The ID of the customer
     * @param customer_Name     The Name of the customer
     * @param address           The Address of the customer
     * @param postal_Code       The Postal code of the customer
     * @param phone             The Phone of the customer
     * @param division_ID       The Division ID of the customer
     * @return                  Returns Result Set to execute the query
     * @throws SQLException     Throws an SQL Exception
     */

    public static int addNewCustomer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, int division_ID) throws SQLException {

        String sqlAdd = "INSERT INTO client_schedule.customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID\n" +
                " VALUES(?,?,?,?,?,?)";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlAdd);

        prepStatement.setInt(1, customer_ID);
        prepStatement.setString(2, customer_Name);
        prepStatement.setString(3, address);
        prepStatement.setString(4, postal_Code);
        prepStatement.setString(5, phone);
        prepStatement.setInt(6, division_ID);

        int resSet = prepStatement.executeUpdate();
        System.out.println(resSet + " item added");
        return resSet;

    }

    ///----------------------------Update Selected Customer------------------------------------------------------///

    /**
     *          Method to Update a selected Customer
     * @param customer_ID       The ID of the customer
     * @param customer_Name     The Name of the customer
     * @param address           The Address of the customer
     * @param postal_Code       The Postal code of the customer
     * @param phone             The Phone of the customer
     * @param division_ID       The Division ID of the customer
     * @return                  Returns Result Set to execute the query
     * @throws SQLException     Throws an SQL Exception
     */

    public static int updateSelectedCustomer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, int division_ID) throws SQLException{

        String sqlUpdate = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?\n" +
                " WHERE Customer_ID = ?";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlUpdate);

        prepStatement.setString(1, customer_Name);
        prepStatement.setString(2, address);
        prepStatement.setString(3, postal_Code);
        prepStatement.setString(4, phone);
        prepStatement.setInt(5, division_ID);
        prepStatement.setInt(6, customer_ID);

        int resSet = prepStatement.executeUpdate();
        System.out.println(resSet + " item updated");
        return resSet;
    }

    ///---------------------------Delete Selected Customer-------------------------------------------------------///

    /**
     *          Method to Delete a selected Customer from the database
     * @param Customer_ID       The ID of the customer
     * @return                  Returns Result Set to execute the query
     * @throws SQLException     Throws an SQL Exception
     */

    public static int deleteSelectedCustomer(int Customer_ID) throws SQLException {

        String sqlDelete = "DELETE FROM customers WHERE Customer_ID = ?";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlDelete);

        prepStatement.setInt(1, Customer_ID);

        int resSet = prepStatement.executeUpdate();
        System.out.println(resSet + " item to be deleted");
        return resSet;

    }









}
