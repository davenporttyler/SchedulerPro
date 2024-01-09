package test.tdavenportc195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import test.tdavenportc195.DAO.AppointmentDAO;
import test.tdavenportc195.DAO.ContactDAO;
import test.tdavenportc195.DAO.CustomerDAO;
import test.tdavenportc195.DAO.UserDAO;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Appointments;
import test.tdavenportc195.Models.Contacts;
import test.tdavenportc195.Models.Customers;
import test.tdavenportc195.Models.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.Supplier;

/**
 *      Add Appointments Controller - 2 Lambda Functions are used here and marked
 *
 * @author Tyler Davenport
 */
public class AppointmentsAddController implements Initializable {


    public Button SaveAddAptButton;
    public Button AddAptBackButton;
    public TextField AddAptIDField;
    public TextField AddAptTypeField;
    public TextField AddAptTitleField;
    public TextField AddAptDescField;
    public ComboBox AddAptContactCombo;
    public ComboBox AddAptStartTimeCombo;
    public ComboBox AddAptEndTimeCombo;
    public DatePicker AddAptStartDatePicker;
    public TextField AddAptLocationField;
    public ComboBox AddAptCustomerCombo;
    public ComboBox AddAptUserCombo;
    public TextField AddAptCustSet;
    public TextField AddAptUserSet;
    public TextField AddAptContactSet;

    ///---------------------------Declared Variables-------------------------------------------------------///
    int randomID = 0;
    int minRandom = (int) (Math.random()*100);
    int maxRandom = (int) (Math.random()*777);






    ///---------------------------------Times-----------------------------------------------------------------------///

    // Get LocalDateTime
    LocalDateTime localDateTime = LocalDateTime.now();

    // Convert
    ZonedDateTime localHomeDateTime = localDateTime.atZone(ZoneId.systemDefault());
    ZonedDateTime utcZonedDateTime = localHomeDateTime.withZoneSameInstant(ZoneOffset.UTC);
    ZonedDateTime estZonedDateTime = localHomeDateTime.withZoneSameInstant(ZoneOffset.of("-05:00"));

    // Get Instant
    Instant utcInstant = utcZonedDateTime.toInstant();
    Instant estInstant = estZonedDateTime.toInstant();

    // Get ZoneID
    ZoneId utcZone = ZoneId.of("UTC");
    ZoneId estZone = ZoneId.of("America/New_York");

    ZonedDateTime utcDateTime = ZonedDateTime.ofInstant(utcInstant, utcZone);
    ZonedDateTime estDateTime = ZonedDateTime.ofInstant(estInstant, estZone);


    //Correct Date time functions from LIS
    LocalTime startTime = LocalTime.of(8, 0);
    LocalDateTime startldt = LocalDateTime.of(LocalDate.now(), startTime);
    ZonedDateTime startzdt = startldt.atZone(ZoneId.of("America/New_York"));
    //ZonedDateTime startzdtUTC = startldt.atZone(ZoneId.of("UTC/Greenwich"));
    ZonedDateTime startdefLdt = startzdt.withZoneSameInstant(ZoneId.systemDefault());
    LocalTime start = LocalTime.of(startdefLdt.getHour(), startdefLdt.getMinute());


    LocalTime endTime = LocalTime.of(22,0);
    LocalDateTime endldt = LocalDateTime.of(LocalDate.now(), endTime);
    ZonedDateTime endzdt = endldt.atZone(ZoneId.of("America/New_York"));
    //ZonedDateTime endzdtUTC = endldt.atZone(ZoneId.of("UTC/Greenwich"));
    ZonedDateTime endDefLdt = endzdt.withZoneSameInstant(ZoneId.systemDefault());
    LocalTime end = LocalTime.of(endDefLdt.getHour(), endDefLdt.getMinute());

    ///---------------------------------------------------------------------------------------------///

    /**
     *      Function to fill the Start and End Combo Boxes
     */
    public void getTimes(){


        while (start.isBefore(end)){
            AddAptStartTimeCombo.getItems().add(start);
            AddAptEndTimeCombo.getItems().add(start.plusMinutes(30));
            start = start.plusMinutes(30);

        }
    }




///--------------Lambda Function 2: Selects a Random meeting Type from my provided Default Observable Array List of types, and sets the Type Field ----///

    private final ObservableList<String> typeSelect = FXCollections.observableArrayList("Team Collaboration Meeting",
            "Client Presentation Meeting", "Brainstorming Session", "Status Update Meeting", "Board of Directors Meeting",
            "One-on-One Meeting", "Training or Workshop", "Sales Pitch Meeting", "Project Kick-off Meeting", "Town Hall Meeting",
            "Customer Feedback Session", "Emergency Response Meeting", "Sprint Planning Meeting", "Annual General Meeting (AGM)",
            "Interview Panel Meeting", "Marketing Strategy Meeting", "Product Development Meeting", "Debriefing Meeting",
            "Social Committee Meeting", "Task Force Meeting");

    private Runnable selectRandomType = () -> {
        Random random = new Random();
        int randomIndex = random.nextInt(typeSelect.size());
        String randomType = typeSelect.get(randomIndex);
        //System.out.println(randomType);
        AddAptTypeField.setText(randomType);
    };


    ///-------------------------------Initialize---------------------------------------------------///

    /**
     *      Initialize Add Appointments Controller
     * @param url               url
     * @param resourceBundle    resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getTimes();
        selectRandomType.run();

        ObservableList<Customers> customerCombo = CustomerDAO.getCustomers();
        ObservableList<Users> userCombo = UserDAO.getAllUsers();
        ObservableList<Contacts> contactCombo = ContactDAO.getAllContacts();

        AddAptCustomerCombo.setItems(customerCombo);
        AddAptUserCombo.setItems(userCombo);
        AddAptContactCombo.setItems(contactCombo);

        AddAptCustomerCombo.setPromptText("Choose Customer");
        AddAptCustomerCombo.setVisibleRowCount(5);
        AddAptUserCombo.setPromptText("Choose User");
        AddAptUserCombo.setVisibleRowCount(5);
        AddAptContactCombo.setPromptText("Choose Contact");
        AddAptContactCombo.setVisibleRowCount(5);


///-------------------Lambda Function 1:    Generates Random Numbers to Add to facilitate more randomness----------------------------------///

        // Lambda function to generate a random integer between 1 and 100 (inclusive)
        Supplier<Integer> randomIntGenerator = () -> {
            Random randomL = new Random();
            return randomL.nextInt(100) + 1;
        };

        // Uses the lambda function to generate random numbers
        int randomNumber1L = randomIntGenerator.get();
        int randomNumber2L = randomIntGenerator.get();

        //Generate random number as upper and lower bounds to add to Java's Math.random to make ID more Unique
        Random random1 = new Random();
        int randomNumber1 = random1.nextInt((int) ((minRandom) * (Math.random() * 333) + randomNumber1L));
        Random random2 = new Random();
        int randomNumber2 = random2.nextInt((int) ((maxRandom) * (Math.random() * 666) + randomNumber2L));

        //Randomise with Java's Math.random using upper and lower random bound
        randomID = (int)(Math.random() * 100) + ((randomNumber1 + randomNumber2) * 69);
        //Auto Set ID field to random number on Add Part screen launch
        AddAptIDField.setText(String.valueOf(randomID));
        AddAptIDField.setEditable(false);


    }

/*

 // Not Used, for loop worked easier as per instructor
    public static boolean checkOverlap(int customer_ID, Timestamp aptStart, Timestamp aptEnd) throws SQLException {

        try {

            String sql = "SELECT * FROM appointments " +
                    "WHERE Customer_ID = ? " +
                    "AND ((? BETWEEN Start AND End) OR (? BETWEEN Start AND End) OR (Start <= ? AND End >= ?))";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = JDBC.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, customer_ID);
            preparedStatement.setObject(2, aptStart);
            preparedStatement.setObject(3, aptEnd);
            preparedStatement.setObject(4, aptStart);
            preparedStatement.setObject(5, aptEnd);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int overlapCount = resultSet.getInt(1);

            // If overlapCount is greater than 0, there is an overlap
            System.out.println("There is an overlap!");
            return overlapCount > 0;


        }
        catch (SQLException e){
            e.printStackTrace();
            return false;

        }

    }

*/



    ///------------------------------Save Appointment----------------------------------------------------///

    /**
     *      Button to Save and Add the appointment
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */
    public void OnSaveAddAppointmentButtonClicked (ActionEvent event) throws SQLException {

        LocalDate date = AddAptStartDatePicker.getValue();
        LocalTime selectedStart = (LocalTime) AddAptStartTimeCombo.getSelectionModel().getSelectedItem();
        LocalTime selectedEnd = (LocalTime) AddAptEndTimeCombo.getSelectionModel().getSelectedItem();

       if (AddAptStartDatePicker.getValue() == null){
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Missing Date");
           alert.setHeaderText("You have not selected a date!");
           alert.setContentText("Date Picker is missing information. Please check entries and try again!");
           alert.showAndWait();

           return;

       }

       DayOfWeek dayStart = LocalDateTime.of(date,selectedStart).toLocalDate().getDayOfWeek();
       DayOfWeek dayEnd = LocalDateTime.of(date,selectedEnd).toLocalDate().getDayOfWeek();

        if (dayStart == DayOfWeek.SATURDAY || dayStart == DayOfWeek.SUNDAY || dayEnd == DayOfWeek.SATURDAY || dayEnd == DayOfWeek.SUNDAY){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Incorrect Date");
            alert.setHeaderText("You have selected a date outside of business operations!");
            alert.setContentText("Please select a day within business operation (Mon-Fri) and try again!");
            alert.showAndWait();

            return;

        }

//Start New code
        LocalDateTime startCheck = LocalDateTime.of(date,selectedStart);
        LocalDateTime endCheck = LocalDateTime.of(date, selectedEnd);

        if (startCheck.isBefore(LocalDateTime.now().minusMinutes(1))){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Incorrect Date / Time");
            alert.setHeaderText("You have selected a time in the past!");
            alert.setContentText("Please select a day and time in the future and try again!");
            alert.showAndWait();

            return;

        }

        for (Appointments apt : AppointmentDAO.getAllAppointments()) {
            LocalDateTime aptStart = apt.getStart();
            LocalDateTime aptEnd = apt.getEnd();

            if (apt.getCustomerId() == Integer.parseInt(AddAptCustSet.getText())) {
                if ((startCheck.isAfter(aptStart) || startCheck.isEqual(aptStart)) &&
                        startCheck.isBefore(aptEnd)) {
                    // Overlap detected - New Appointment is starting on or before previous start && before existing appointment end time
                    // (Within Existing Appointment)
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Time Conflict");
                    alert.setHeaderText("Overlapping Appointment times!");
                    alert.setContentText("Overlapping Appointment times. Please select valid times and try again!");
                    alert.showAndWait();
                    return;

                } else if ((endCheck.isBefore(aptEnd) || endCheck.isEqual(aptEnd)) &&
                        endCheck.isAfter(aptStart)) {
                    // Overlap detected - New Appointment end time is before or equal to and existing end time && after any existing appointments start time
                    // (Overlaps on any start or end)
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Time Conflict");
                    alert.setHeaderText("Overlapping Appointment times!");
                    alert.setContentText("Overlapping Appointment times. Please select valid times and try again!");
                    alert.showAndWait();
                    return;

                }else if ((startCheck.isBefore(aptStart) || startCheck.isEqual(aptStart)) &&
                        endCheck.isAfter(aptEnd)) {
                    // Overlap detected - Encapsulated appointment
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Time Conflict");
                    alert.setHeaderText("Overlapping Appointment times!");
                    alert.setContentText("Overlapping Appointment times. Please select valid times and try again!");
                    alert.showAndWait();
                    return; // Return to exit the function
                }
            }
        }

//End New Code

        String saveDTStart = LocalDateTime.of(date, selectedStart).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String saveDTEnd = LocalDateTime.of(date, selectedEnd).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        if (selectedEnd.isBefore(selectedStart) || selectedStart.equals(selectedEnd)
                || saveDTStart.equals(saveDTEnd)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Time Conflict");
            alert.setHeaderText("You have selected conflicting times!");
            alert.setContentText("Start and End times are conflicting. Please select valid times and try again!");
            alert.showAndWait();

            AddAptStartTimeCombo.getSelectionModel().clearSelection();
            AddAptEndTimeCombo.getSelectionModel().clearSelection();

            return;

        }


        if (AddAptContactCombo.getSelectionModel().isEmpty() ||
                AddAptCustomerCombo.getSelectionModel().isEmpty() ||
                AddAptUserCombo.getSelectionModel().isEmpty() ||
                AddAptStartDatePicker.getValue() == null ||
                AddAptTypeField.getText().isEmpty() ||
                AddAptTitleField.getText().isEmpty() ||
                AddAptDescField.getText().isEmpty() ||
                AddAptLocationField.getText().isEmpty() ||
                AddAptIDField.getText().isEmpty() ){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Missing Info");
            alert.setHeaderText("You have missing info!");
            alert.setContentText("One of more field are missing information. Please check entries and try again!");
            alert.showAndWait();

        }


        else {

            try {
                String sqlInsert = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, " +
                        "Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


                PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlInsert);

                prepStatement.setInt(1, Integer.parseInt(AddAptIDField.getText()));
                prepStatement.setString(2, AddAptTitleField.getText());
                prepStatement.setString(3, AddAptDescField.getText());
                prepStatement.setString(4, AddAptLocationField.getText());
                prepStatement.setString(5, AddAptTypeField.getText());
                prepStatement.setTimestamp(6, Timestamp.valueOf(saveDTStart));
                prepStatement.setTimestamp(7, Timestamp.valueOf(saveDTEnd));
                prepStatement.setInt(8, Integer.parseInt(AddAptCustSet.getText()));
                prepStatement.setInt(9, Integer.parseInt(AddAptUserSet.getText()));
                prepStatement.setInt(10, Integer.parseInt(AddAptContactSet.getText()));

                prepStatement.execute();

                Stage stage = (Stage)  SaveAddAptButton.getScene().getWindow();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
                stage.setTitle("C195 TDavenport Appointment Management System");
                stage.setScene(new Scene(root));
                stage.show();


            }
            catch (SQLException | IOException e){
                System.out.println(e);

            }

        }






    }

    ///--------------------------------Back Button--------------------------------------------------///

    /**
     *      Button to Exit the Add Appointment Page
     * @param event         Action Event
     * @throws IOException  IOException
     */
    public void OnAppointmentsBackButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  AddAptBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }

    ///------------------------------------------------------------------------------------------------///
    /**
     *          When Customer Combo box has a selected item, it queries the database to find the matching Customer ID and sets the label
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */
    public void whenCustomerComboSelected(ActionEvent event) throws SQLException{

        Object customerIDSet = AddAptCustomerCombo.getSelectionModel().getSelectedItem();

        //sql Select Statement
        String sqlSelect = "SELECT Customer_ID FROM customers WHERE Customer_Name = '" + customerIDSet + "'";
        //Test
        //System.out.println(customerIDSet);
        //System.out.println(sqlSelect);
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                AddAptCustSet.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///--------------------------------------------------------------------------------------------///
    /**
     *          When User Combo box has a selected item, it queries the database to find the matching User ID and sets the label
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */
    public void whenUserComboSelected(ActionEvent event) throws SQLException{

        Object userIDSet = AddAptUserCombo.getSelectionModel().getSelectedItem();

        //sql Select Statement
        String sqlSelect = "SELECT User_ID FROM users WHERE User_Name = '" + userIDSet + "'";
        //Test
        //System.out.println(userIDSet);
        //System.out.println(sqlSelect);
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                AddAptUserSet.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///-----------------------------------------------------------------------------------------------///
    /**
     *          When Contact Combo box has a selected item, it queries the database to find the matching Contact ID and sets the label
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */

    public void whenContactComboSelected(ActionEvent event) throws SQLException{

        Object contactIDSet = AddAptContactCombo.getSelectionModel().getSelectedItem();

        //sql Select Statement
        String sqlSelect = "SELECT Contact_ID FROM contacts WHERE Contact_Name = '" + contactIDSet + "'";
        //Test
        //System.out.println(contactIDSet);
        //System.out.println(sqlSelect);
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                AddAptContactSet.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
