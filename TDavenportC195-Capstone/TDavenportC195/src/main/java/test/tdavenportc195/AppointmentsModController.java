package test.tdavenportc195;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *          Appointments Modify Controller
 *
 * @author Tyler Davenport
 */
public class AppointmentsModController implements Initializable {
    public Button SaveModAptButton;
    public Button ModAptBackButton;
    public TextField ModAptIDField;
    public TextField ModAptTypeField;
    public TextField ModAptTitleField;
    public TextField ModAptDescriptionField;
    public ComboBox ModAptContactCombo;
    public ComboBox ModAptStartCombo;
    public ComboBox ModAptEndCombo;
    public DatePicker ModAptDatePicker;
    public TextField ModAptLocationField;
    public ComboBox ModAptUserCombo;
    public ComboBox ModAptCustomerCombo;
    public TextField ModAptCustomerSet;
    public TextField ModAptUserSet;
    public TextField ModAptContactSet;

    int selectedContact = 0;
    int selectedCustomer = 0;
    int selectedUser = 0;

    //Correct Date time functions from LIS
    LocalTime startTime = LocalTime.of(8, 0);
    LocalDateTime startldt = LocalDateTime.of(LocalDate.now(), startTime);
    ZonedDateTime startzdt = startldt.atZone(ZoneId.of("America/New_York"));
    ZonedDateTime startdefLdt = startzdt.withZoneSameInstant(ZoneId.systemDefault());
    LocalTime start = LocalTime.of(startdefLdt.getHour(), startdefLdt.getMinute());


    LocalTime endTime = LocalTime.of(22,0);
    LocalDateTime endldt = LocalDateTime.of(LocalDate.now(), endTime);
    ZonedDateTime endzdt = endldt.atZone(ZoneId.of("America/New_York"));
    ZonedDateTime endDefLdt = endzdt.withZoneSameInstant(ZoneId.systemDefault());
    LocalTime end = LocalTime.of(endDefLdt.getHour(), endDefLdt.getMinute());

    /**
     *      Function to fill the Start and End Combo boxes
     */
    public void getTimes(){

        while (start.isBefore(end)){
            ModAptStartCombo.getItems().add(start);
            ModAptEndCombo.getItems().add(start.plusMinutes(30));
            start = start.plusMinutes(30);

        }
    }


    ///----------------------------------------------------------------------------------///

    private Appointments selectedAppointment;

    /**
     *      Gets and loads the Selected Appointment from the Appointments TableView
     * @param appointments      Appointment to be Modified
     */
    public void loadSelectedAppointment(Appointments appointments){

        selectedAppointment = appointments;

        ModAptIDField.setText(String.valueOf(selectedAppointment.getId()));
        ModAptTitleField.setText(selectedAppointment.getTitle());
        ModAptDescriptionField.setText(selectedAppointment.getDescription());
        ModAptLocationField.setText(selectedAppointment.getLocation());
        ModAptTypeField.setText(selectedAppointment.getType());

        //Get Timestamp and split it with Regex into usable data
        String startTimestamp = String.valueOf(selectedAppointment.getStart());
        String endTimestamp = String.valueOf(selectedAppointment.getEnd());

        String[] startSplit = startTimestamp.split("T");
        String[] endSplit = endTimestamp.split("T");

        LocalDate startSplitDate = LocalDate.parse(startSplit[0]);
        LocalDate endSplitDate = LocalDate.parse(endSplit[0]);

        LocalTime startSplitTime = LocalTime.parse(startSplit[1]);
        LocalTime endSplitTime = LocalTime.parse(endSplit[1]);

        ModAptStartCombo.setValue(startSplitTime);
        ModAptEndCombo.setValue(endSplitTime);
        ModAptDatePicker.setValue(startSplitDate);

        ModAptContactCombo.setValue(selectedAppointment.getContactName());
        ModAptCustomerCombo.setValue(selectedAppointment.getCustomerName());
        ModAptUserCombo.setValue(selectedAppointment.getUserName());

        ModAptContactSet.setText(String.valueOf(selectedAppointment.getContactId()));
        ModAptCustomerSet.setText(String.valueOf(selectedAppointment.getCustomerId()));
        ModAptUserSet.setText(String.valueOf(selectedAppointment.getUserId()));


    }

    ///----------------------------------------------------------------------------------------------------///
    /**
     *          Initialized upon loading the Modify Appointments
     * @param url               url
     * @param resourceBundle    Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getTimes();

        ObservableList<Customers> customerCombo = CustomerDAO.getCustomers();
        ObservableList<Users> userCombo = UserDAO.getAllUsers();
        ObservableList<Contacts> contactCombo = ContactDAO.getAllContacts();

        ModAptCustomerCombo.setItems(customerCombo);
        ModAptUserCombo.setItems(userCombo);
        ModAptContactCombo.setItems(contactCombo);

        ModAptCustomerCombo.setVisibleRowCount(5);
        ModAptUserCombo.setVisibleRowCount(5);
        ModAptContactCombo.setVisibleRowCount(5);


    }





    ///----------------------------------------------------------------------------------///

    /**
     *      Button to save the Modified Appointment
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnSaveModAppointmentButtonClicked (ActionEvent event) throws IOException {


            LocalDate date = ModAptDatePicker.getValue();
            LocalTime selectedStart = (LocalTime) ModAptStartCombo.getSelectionModel().getSelectedItem();
            LocalTime selectedEnd = (LocalTime) ModAptEndCombo.getSelectionModel().getSelectedItem();

        if (ModAptDatePicker.getValue() == null){
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

            if (apt.getCustomerId() == Integer.parseInt(ModAptCustomerSet.getText())) {
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

        if (selectedEnd.isBefore(selectedStart) || selectedStart.equals(selectedEnd) ||saveDTStart.equals(saveDTEnd)){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Time Conflict");
            alert.setHeaderText("You have selected conflicting times!");
            alert.setContentText("Start and End times are conflicting. Please select valid times and try again!");
            alert.showAndWait();

            ModAptStartCombo.getSelectionModel().clearSelection();
            ModAptEndCombo.getSelectionModel().clearSelection();
            return;

        }
        if (
                ModAptContactSet.getText().isEmpty() ||
                ModAptUserSet.getText().isEmpty() ||
                ModAptCustomerSet.getText().isEmpty() ||
                ModAptDatePicker.getValue() == null ||
                ModAptTypeField.getText().isEmpty() ||
                ModAptTitleField.getText().isEmpty() ||
                ModAptDescriptionField.getText().isEmpty() ||
                ModAptLocationField.getText().isEmpty() ||
                ModAptIDField.getText().isEmpty() ){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Missing Info");
            alert.setHeaderText("You have missing info!");
            alert.setContentText("One of more field are missing information. Please check entries and try again!");
            alert.showAndWait();

        }
        else {

            try {
                String sqlUpdate = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?,\n" +
                        "Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

                //See If statement is normal
                //System.out.println(sqlUpdate);

                PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlUpdate);

                prepStatement.setString(1, ModAptTitleField.getText());
                prepStatement.setString(2, ModAptDescriptionField.getText());
                prepStatement.setString(3, ModAptLocationField.getText());
                prepStatement.setString(4, ModAptTypeField.getText());
                prepStatement.setTimestamp(5, Timestamp.valueOf(saveDTStart));
                prepStatement.setTimestamp(6, Timestamp.valueOf(saveDTEnd));
                prepStatement.setInt(7, Integer.parseInt(ModAptCustomerSet.getText()));
                prepStatement.setInt(8, Integer.parseInt(ModAptUserSet.getText()));
                prepStatement.setInt(9, Integer.parseInt(ModAptContactSet.getText()));
                prepStatement.setInt(10, Integer.parseInt(ModAptIDField.getText()));

                prepStatement.execute();

                Stage stage = (Stage)  SaveModAptButton.getScene().getWindow();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
                stage.setTitle("C195 TDavenport Appointment Management System");
                stage.setScene(new Scene(root));
                stage.show();

            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }






    }
    ///--------------------------Back Button--------------------------------------------------------///

    /**
     *      Button to go Back to the Main Appointments page
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnAppointmentBackButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  ModAptBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     *          When Customer Combo box has a selected item, it queries the database to find the matching Customer ID and sets the label
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */
    public void whenModCustComboClicked(ActionEvent event) throws SQLException {

        Object customerIDSet = ModAptCustomerCombo.getSelectionModel().getSelectedItem();

        //sql Select Statement
        String sqlSelect = "SELECT Customer_ID FROM customers WHERE Customer_Name = '" + customerIDSet + "'";
        //Prep Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                ModAptCustomerSet.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///-----------------------------------------------------------------------------------------///

    /**
     *          When User Combo box has a selected item, it queries the database to find the matching User ID and sets the label
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */
    public void whenModUserComboClicked(ActionEvent event) throws SQLException {

        Object userIDSet = ModAptUserCombo.getSelectionModel().getSelectedItem();

        //sql Select Statement
        String sqlSelect = "SELECT User_ID FROM users WHERE User_Name = '" + userIDSet + "'";
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                ModAptUserSet.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ///--------------------------------------------------------------------------------------///

    /**
     *          When Contact Combo box has a selected item, it queries the database to find the matching Contact ID and sets the label
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */
    public void whenModContComboClicked(ActionEvent event) throws SQLException {

        Object contactIDSet = ModAptContactCombo.getSelectionModel().getSelectedItem();

        //sql Select Statement
        String sqlSelect = "SELECT Contact_ID FROM contacts WHERE Contact_Name = '" + contactIDSet + "'";
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                ModAptContactSet.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }











}
