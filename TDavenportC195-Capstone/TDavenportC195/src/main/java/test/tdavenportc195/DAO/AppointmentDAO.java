package test.tdavenportc195.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *          The AppointmentDAO Class is for creating methods to query database
 *
 * @author  Tyler Davenport
 */

public class AppointmentDAO {


    ///--------------------------------Get All Appointments--------------------------------------------------///

     /**        Method to query the database and get all appointments
     * @return  ObservableList allAppointments (All Appointments)
     */

    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {

            //SQL Select Statement
            String sqlSelect = "SELECT * FROM appointments";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                int appointment_ID = resSet.getInt("Appointment_ID");
                String title = resSet.getString("Title");
                String description = resSet.getString("Description");
                String location = resSet.getString("Location");
                String type = resSet.getString("Type");
                LocalDateTime start = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resSet.getTimestamp("End").toLocalDateTime();
                int customer_ID = resSet.getInt("Customer_ID");
                int user_ID = resSet.getInt("User_ID");
                int contact_ID = resSet.getInt("Contact_ID");

                Appointments newAppointment = new Appointments(appointment_ID, title, description, location, type, start,
                        end, customer_ID, user_ID, contact_ID);

                allAppointments.add(newAppointment);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allAppointments;

    }

    ///--------------------------------Get All Appointments + Join Tables for Names-------------------------------------------------///

    /**
     *          Method to query the database and get relevant appointment, user, customer, and contact info for TableView
     * @return  ObservableList allAppointments (All Appointments and user, customer, contact ID and Names)
     */

    public static ObservableList<Appointments> getAllAppointmentsAndPeople() {

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {

            //SQL Select Statement
            String sqlSelect = "SELECT A.Appointment_ID, A.Title, A.Description, A.Location, A.Type, A.Start, A.End, \n" +
                    "A.Customer_ID, A.User_ID, A.Contact_ID, B.User_Name, C.Customer_Name, D.Contact_Name\n" +
                    "FROM appointments A, users B, customers C, contacts D\n" +
                    "WHERE A.User_ID = B.User_ID\n" +
                    "AND A.Customer_ID = C.Customer_ID\n" +
                    "AND A.Contact_ID = D.Contact_ID;";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                int appointment_ID = resSet.getInt("Appointment_ID");
                String title = resSet.getString("Title");
                String description = resSet.getString("Description");
                String location = resSet.getString("Location");
                String type = resSet.getString("Type");
                LocalDateTime start = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resSet.getTimestamp("End").toLocalDateTime();
                int customer_ID = resSet.getInt("Customer_ID");
                int user_ID = resSet.getInt("User_ID");
                int contact_ID = resSet.getInt("Contact_ID");
                String customer_Name = resSet.getString("Customer_Name");
                String user_Name = resSet.getString("User_Name");
                String contact_Name = resSet.getString("Contact_Name");

                Appointments newAppointment = new Appointments(appointment_ID, title, description, location, type, start,
                        end, customer_ID, user_ID, contact_ID, customer_Name, user_Name, contact_Name);

                allAppointments.add(newAppointment);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allAppointments;

    }


    ///--------------------------------Get All Appointments + Join Tables for Names-------------------------------------------------///

    /**
     *          Method to query the database to obtain all relevant upcoming appointments for the user who logged in
     * @return  ObservableList allAppointments (Appointments between Now and 15 minutes upcoming)
     */

    public static String getAllLoginAppointments() {

        String localDTNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String localDT15MinFuture = LocalDateTime.now().plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        int appointment_ID = 0;
        LocalDateTime start = null;

        try {

            //TODO Add in User search to filter

            //SQL Select Statement
            String sqlSelect = "SELECT Appointment_ID, Start FROM appointments \n" +
                    "WHERE Start BETWEEN {ts '" + localDTNow + "'} AND {ts '"+ localDT15MinFuture + "'};";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                appointment_ID = resSet.getInt("Appointment_ID");
                start = resSet.getTimestamp("Start").toLocalDateTime();

                Appointments newAppointment = new Appointments(appointment_ID,  start);

                allAppointments.add(newAppointment);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return "Appointment ID: " + appointment_ID + "\n" +
               "Start: " + start;

    }


    ///--------------------------------Get All Appointments + Join Tables for Names-------------------------------------------------///

    /**
     *          Method to query the database to obtain appointment data from previous month
     * @return  ObservableList allAppointments (All appointments for the Past Month)
     */

    public static ObservableList<Appointments> getAllAppointmentsAndPeopleByMonth() {

        String localDTNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String localDT1MonthAgo = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {

            //SQL Select Statement
            String sqlSelect = "SELECT A.Appointment_ID, A.Title, A.Description, A.Location, A.Type, A.Start, A.End, \n" +
                    "A.Customer_ID, A.User_ID, A.Contact_ID, B.User_Name, C.Customer_Name, D.Contact_Name\n" +
                    "FROM appointments A, users B, customers C, contacts D\n" +
                    "WHERE A.User_ID = B.User_ID\n" +
                    "AND A.Customer_ID = C.Customer_ID\n" +
                    "AND A.Contact_ID = D.Contact_ID\n" +
                    "AND A.Start BETWEEN {ts '" + localDT1MonthAgo + "'} AND {ts '"+ localDTNow + "'};";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                int appointment_ID = resSet.getInt("Appointment_ID");
                String title = resSet.getString("Title");
                String description = resSet.getString("Description");
                String location = resSet.getString("Location");
                String type = resSet.getString("Type");
                LocalDateTime start = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resSet.getTimestamp("End").toLocalDateTime();
                int customer_ID = resSet.getInt("Customer_ID");
                int user_ID = resSet.getInt("User_ID");
                int contact_ID = resSet.getInt("Contact_ID");
                String customer_Name = resSet.getString("Customer_Name");
                String user_Name = resSet.getString("User_Name");
                String contact_Name = resSet.getString("Contact_Name");

                Appointments newAppointment = new Appointments(appointment_ID, title, description, location, type, start,
                        end, customer_ID, user_ID, contact_ID, customer_Name, user_Name, contact_Name);

                allAppointments.add(newAppointment);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allAppointments;

    }

    ///--------------------------------Get All Appointments + Join Tables for Names-------------------------------------------------///

    /**
     *          Method to query the database and obtain all the Appointment data of the previous week
     * @return  ObservableList allAppointments (All appointments from past week)
     */

    public static ObservableList<Appointments> getAllAppointmentsAndPeopleByWeek() {

        String localDTNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String localDT1WeekAgo = LocalDateTime.now().minusWeeks(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {

            //SQL Select Statement
            String sqlSelect = "SELECT A.Appointment_ID, A.Title, A.Description, A.Location, A.Type, A.Start, A.End, \n" +
                    "A.Customer_ID, A.User_ID, A.Contact_ID, B.User_Name, C.Customer_Name, D.Contact_Name\n" +
                    "FROM appointments A, users B, customers C, contacts D\n" +
                    "WHERE A.User_ID = B.User_ID\n" +
                    "AND A.Customer_ID = C.Customer_ID\n" +
                    "AND A.Contact_ID = D.Contact_ID\n" +
                    "AND A.Start BETWEEN {ts '" + localDT1WeekAgo + "'} AND {ts '"+ localDTNow + "'};";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                int appointment_ID = resSet.getInt("Appointment_ID");
                String title = resSet.getString("Title");
                String description = resSet.getString("Description");
                String location = resSet.getString("Location");
                String type = resSet.getString("Type");
                LocalDateTime start = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resSet.getTimestamp("End").toLocalDateTime();
                int customer_ID = resSet.getInt("Customer_ID");
                int user_ID = resSet.getInt("User_ID");
                int contact_ID = resSet.getInt("Contact_ID");
                String customer_Name = resSet.getString("Customer_Name");
                String user_Name = resSet.getString("User_Name");
                String contact_Name = resSet.getString("Contact_Name");

                Appointments newAppointment = new Appointments(appointment_ID, title, description, location, type, start,
                        end, customer_ID, user_ID, contact_ID, customer_Name, user_Name, contact_Name);

                allAppointments.add(newAppointment);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allAppointments;

    }

    ///--------------------------------Get All Appointments + Join Tables for Names-------------------------------------------------///

    /**
     *          Method to query the database to obtain upcoming appointments for next week
     * @return  ObservableList allAppointments (Appointments for the upcoming week)
     */

    public static ObservableList<Appointments> getAllAppointmentsAndPeopleByUpcomingWeek() {

        String localDTNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String localDT1WeekFuture = LocalDateTime.now().plusWeeks(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {

            //SQL Select Statement
            String sqlSelect = "SELECT A.Appointment_ID, A.Title, A.Description, A.Location, A.Type, A.Start, A.End, \n" +
                    "A.Customer_ID, A.User_ID, A.Contact_ID, B.User_Name, C.Customer_Name, D.Contact_Name\n" +
                    "FROM appointments A, users B, customers C, contacts D\n" +
                    "WHERE A.User_ID = B.User_ID\n" +
                    "AND A.Customer_ID = C.Customer_ID\n" +
                    "AND A.Contact_ID = D.Contact_ID\n" +
                    "AND A.Start BETWEEN {ts '" + localDTNow + "'} AND {ts '"+ localDT1WeekFuture + "'};";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                int appointment_ID = resSet.getInt("Appointment_ID");
                String title = resSet.getString("Title");
                String description = resSet.getString("Description");
                String location = resSet.getString("Location");
                String type = resSet.getString("Type");
                LocalDateTime start = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resSet.getTimestamp("End").toLocalDateTime();
                int customer_ID = resSet.getInt("Customer_ID");
                int user_ID = resSet.getInt("User_ID");
                int contact_ID = resSet.getInt("Contact_ID");
                String customer_Name = resSet.getString("Customer_Name");
                String user_Name = resSet.getString("User_Name");
                String contact_Name = resSet.getString("Contact_Name");

                Appointments newAppointment = new Appointments(appointment_ID, title, description, location, type, start,
                        end, customer_ID, user_ID, contact_ID, customer_Name, user_Name, contact_Name);

                allAppointments.add(newAppointment);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allAppointments;

    }

    /**
     *          Method to query the database to obtain upcoming appointments for next month
     * @return  ObservableList allAppointments (Appointments for the upcoming month)
     */

    public static ObservableList<Appointments> getAllAppointmentsAndPeopleByUpcomingMonth() {

        String localDTNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String localDT1MonthFuture = LocalDateTime.now().plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

        try {

            //SQL Select Statement
            String sqlSelect = "SELECT A.Appointment_ID, A.Title, A.Description, A.Location, A.Type, A.Start, A.End, \n" +
                    "A.Customer_ID, A.User_ID, A.Contact_ID, B.User_Name, C.Customer_Name, D.Contact_Name\n" +
                    "FROM appointments A, users B, customers C, contacts D\n" +
                    "WHERE A.User_ID = B.User_ID\n" +
                    "AND A.Customer_ID = C.Customer_ID\n" +
                    "AND A.Contact_ID = D.Contact_ID\n" +
                    "AND A.Start BETWEEN {ts '" + localDTNow + "'} AND {ts '"+ localDT1MonthFuture + "'};";
            //Prepared Statement
            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
            //ResultSet
            ResultSet resSet = prepStatement.executeQuery();

            while (resSet.next()) {
                int appointment_ID = resSet.getInt("Appointment_ID");
                String title = resSet.getString("Title");
                String description = resSet.getString("Description");
                String location = resSet.getString("Location");
                String type = resSet.getString("Type");
                LocalDateTime start = resSet.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = resSet.getTimestamp("End").toLocalDateTime();
                int customer_ID = resSet.getInt("Customer_ID");
                int user_ID = resSet.getInt("User_ID");
                int contact_ID = resSet.getInt("Contact_ID");
                String customer_Name = resSet.getString("Customer_Name");
                String user_Name = resSet.getString("User_Name");
                String contact_Name = resSet.getString("Contact_Name");

                Appointments newAppointment = new Appointments(appointment_ID, title, description, location, type, start,
                        end, customer_ID, user_ID, contact_ID, customer_Name, user_Name, contact_Name);

                allAppointments.add(newAppointment);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
        return allAppointments;
    }


    ///----------------------------Add New Appointment------------------------------------------------------///

    /**
     *          Method to Add a new appointment into the database
     * @param appointment_ID    The ID of the appointment
     * @param title             The Title of the appointment
     * @param description       The Description of the appointment
     * @param location          The Location of the appointment
     * @param type              The Type of appointment
     * @param start             The Start of the appointment
     * @param end               The End of the appointment
     * @param customer_ID       The CustomerID associated to the appointment
     * @param user_ID           The UserID associated to the appointment
     * @param contact_ID        The ContactID associated to the appointment
     * @return                  Returns Result Set to execute the query
     * @throws SQLException     Throws an SQL Exception
     */

    public static int addNewAppointment(int appointment_ID, String title, String description, String location,
                                        String type, LocalDateTime start, LocalDateTime end, int customer_ID,
                                        int user_ID, int contact_ID) throws SQLException {

        String sqlAdd = "INSERT INTO client_schedule.appointments (Appointment_ID, Title, Description, Location, \n" +
                "Type, Start, End, Customer_ID, User_ID, Contact_ID) \n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlAdd);

        prepStatement.setInt(1, appointment_ID);
        prepStatement.setString(2, title);
        prepStatement.setString(3, description);
        prepStatement.setString(4, location);
        prepStatement.setString(5, type);
        prepStatement.setTimestamp(6, Timestamp.valueOf(start));
        prepStatement.setTimestamp(7, Timestamp.valueOf(end));
        prepStatement.setInt(8, customer_ID);
        prepStatement.setInt(9, user_ID);
        prepStatement.setInt(10, contact_ID);

        int resSet = prepStatement.executeUpdate();
        return resSet;
    }

    ///----------------------------Update Selected Customer------------------------------------------------------///

    /**
     *          Method to Update and existing appointment in the database
     * @param appointment_ID    The ID of the appointment
     * @param title             The Title of the appointment
     * @param description       The Description of the appointment
     * @param location          The Location of the appointment
     * @param type              The Type of appointment
     * @param start             The Start of the appointment
     * @param end               The End of the appointment
     * @param customer_ID       The CustomerID associated to the appointment
     * @param user_ID           The UserID associated to the appointment
     * @param contact_ID        The ContactID associated to the appointment
     * @return                  Returns Result Set to execute the query
     * @throws SQLException     Throws an SQL Exception
     */

    public static int updateSelectedAppointment(int appointment_ID, String title, String description, String location,
                                                String type, LocalDateTime start, LocalDateTime end, int customer_ID,
                                                int user_ID, int contact_ID) throws SQLException {

        String sqlUpdate = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?,\n" +
                "End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? \n" +
                "WHERE Appointment_ID = ?";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlUpdate);

        prepStatement.setString(1, title);
        prepStatement.setString(2, description);
        prepStatement.setString(3, location);
        prepStatement.setString(4, type);
        prepStatement.setTimestamp(5, Timestamp.valueOf(start));
        prepStatement.setTimestamp(6, Timestamp.valueOf(end));
        prepStatement.setInt(7, customer_ID);
        prepStatement.setInt(8, user_ID);
        prepStatement.setInt(9, contact_ID);
        prepStatement.setInt(10, appointment_ID);

        int resSet = prepStatement.executeUpdate();
        System.out.println(resSet + " item updated");
        return resSet;
    }


    ///-----------------------------Delete Selected Appointment-----------------------------------------------------///

    /**
     *          Method to Delete a selected appointment from the database
     * @param Appointment_ID        AppointmentID of the selected appointment
     * @return                      Returns Result Set to execute the query
     * @throws SQLException         Throws an SQL Exception
     */

    public static int deleteSelectedAppointment(int Appointment_ID) throws SQLException {

        String sqlDelete = "DELETE FROM appointments WHERE Appointment_ID = ?";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlDelete);

        prepStatement.setInt(1, Appointment_ID);

        int resSet = prepStatement.executeUpdate();
        System.out.println(resSet + " item to be deleted");
        return resSet;
    }


///-----------------------------Delete Selected Appointment by Customer ID-----------------------------------------------------///

    /**
     *          Method to Delete a selected appointment from the database
     * @param Customer_ID           Customer ID of the selected appointment
     * @return                      Returns Result Set to execute the query
     * @throws SQLException         Throws an SQL Exception
     */

    public static int deleteSelectedAppointmentCustomer(int Customer_ID) throws SQLException {

        String sqlDelete = "DELETE FROM appointments WHERE Customer_ID = ?";

        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlDelete);

        prepStatement.setInt(1, Customer_ID);

        int resSet = prepStatement.executeUpdate();
        System.out.println(resSet + " Customer Appointments to be deleted");
        return resSet;
    }







}
