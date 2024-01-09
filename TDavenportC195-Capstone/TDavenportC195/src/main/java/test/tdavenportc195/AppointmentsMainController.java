package test.tdavenportc195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import test.tdavenportc195.DAO.AppointmentDAO;
import test.tdavenportc195.DAO.CustomerDAO;
import test.tdavenportc195.Models.Appointments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *      Appointments Main Controller
 *
 * @author Tyler Davenport
 */
public class AppointmentsMainController implements Initializable {


    public TableColumn<Appointments, Integer> AptContactIDCol;
    public RadioButton allViewRadio;
    public RadioButton weekViewRadio;
    public RadioButton monthViewRadio;
    public ToggleGroup viewRadioGroup;
    @FXML
    public TableColumn<Appointments, String> AptContactNameCol;
    @FXML
    public TableColumn<Appointments, String> AptCustomerNameCol;
    @FXML
    public TableColumn<Appointments, String> AptUserNameCol;
    public RadioButton upcomingWeekRadio;
    public RadioButton upcomingMonthRadio;
    @FXML
    private Button AddAptButton;
    @FXML
    public TableColumn<Appointments, Integer> AptContactCol;
    @FXML
    public TableColumn<Appointments, Integer> AptCustIDCol;
    @FXML
    public TableColumn<Appointments, String> AptDescriptionCol;
    @FXML
    public TableColumn<Appointments, LocalDateTime> AptEndCol;
    @FXML
    public TableColumn<Appointments, Integer> AptIDCol;
    @FXML
    public TableColumn<Appointments, String> AptLocationCol;
    @FXML
    private Button AptMainHomeButton;
    @FXML
    public TableColumn<Appointments, LocalDateTime> AptStartCol;
    @FXML
    private TableView<Appointments> AptTableView;
    @FXML
    public TableColumn<Appointments, String> AptTitleCol;
    @FXML
    public TableColumn<Appointments, String> AptTypeCol;
    @FXML
    public TableColumn<Appointments, Integer> AptUserIDCol;
    @FXML
    private Button DeleteAptButton;
    @FXML
    private Button ModAptButton;

    Stage stage;


    ///--------------------------Initialize--------------------------------------------------------///

    /**
     *      Initialized upon Loading the Appointments Main
     * @param url               url
     * @param resourceBundle    ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AptIDCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        AptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        AptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        AptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        AptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        AptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        AptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        AptCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        AptUserIDCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        AptContactCol.setCellValueFactory(new PropertyValueFactory<>("ContactId"));
        AptCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AptContactNameCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        AptUserNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));

        AptTableView.setItems(AppointmentDAO.getAllAppointmentsAndPeople());


    }



    ///------------------------Add Appointment----------------------------------------------------------///

    /**
     *      Button for Adding an Appointment
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnAddAppointmentButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  AddAptButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsAdd.fxml")));
        stage.setTitle("C195 TDavenport Add Appointment");
        stage.setScene(new Scene(root));
        stage.show();

    }

    ///-------------------------Modify Appointment---------------------------------------------------------///

    /**
     *      Button for Modifying an Appointment, Confirms an appointment is selected
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnModAptButtonClicked (ActionEvent event) throws IOException {

        if (AptTableView.getSelectionModel().getSelectedItem() == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Error");
            alert.setContentText("Please Confirm: You must select an Appointment to Modify!");
            alert.showAndWait();

        }
        else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AppointmentsMod.fxml"));
            Parent root = loader.load();

            Scene appointmentModScene = new Scene(root);

            AppointmentsModController appointmentsModController = loader.getController();
            appointmentsModController.loadSelectedAppointment(AptTableView.getSelectionModel().getSelectedItem());

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(appointmentModScene);
            window.show();

        }
    }

    ///----------------------------Delete Appointment------------------------------------------------------///

    /**
     *      Button for Deleting an Appointment, Confirms and Appointment is selected
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */
    public void OnDelAptButtonClicked (ActionEvent event) throws SQLException {

        if (AptTableView.getSelectionModel().getSelectedItem() == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Error");
            alert.setContentText("Please Confirm: You must select an Appointment to Delete!");
            alert.showAndWait();
        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Confirm Delete");
            alert.setContentText("Please Confirm You Want to Delete Appointment");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK){

                int Appointment_ID = AptTableView.getSelectionModel().getSelectedItem().getId();
                int success = AppointmentDAO.deleteSelectedAppointment(Appointment_ID);
                String Type = AptTableView.getSelectionModel().getSelectedItem().getType();

                if (success > 0 ){

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Appointment Deleted");
                    alert2.setHeaderText("Appointment Deleted");
                    alert2.setContentText("Selected Appointment with ID#: " + Appointment_ID +
                            " and Type: "+ Type + " has been successfully deleted.");
                    alert2.showAndWait();

                    allViewRadio.setSelected(true);
                    AptTableView.getItems().clear();
                    AptTableView.setItems(AppointmentDAO.getAllAppointments());

                }
                else {
                    System.out.println("Appointment not found");
                }
            }
        }
    }

    ///----------------------------Home Button------------------------------------------------------///

    /**
     *      Button for Exiting the Appointments screen, and going back to Home screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnHomeButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  AptTableView.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }


    ///------------------------------------Radio Button Action Events------------------------------------------///

    /**
     *      Changes TableView to show only Appointments from the past month
     * @param event     Action Event
     */
    public void whenMonthlySelected(ActionEvent event) {

        AptTableView.setItems(AppointmentDAO.getAllAppointmentsAndPeopleByMonth());
    }

    /**
     *      Changes TableView to show only Appointments from the past week
     * @param event     Action Event
     */
    public void whenWeeklySelected(ActionEvent event) {

        AptTableView.setItems(AppointmentDAO.getAllAppointmentsAndPeopleByWeek());
    }

    /**
     *      Changes TableView to show all Appointments
     * @param event     Action Event
     */
    public void whenAllAppointmentsSelected(ActionEvent event) {

        AptTableView.setItems(AppointmentDAO.getAllAppointmentsAndPeople());
    }

    /**
     *      Changes TableView to show only Appointments in the upcoming week
     * @param event     Action Event
     */
    public void whenUpcomingWeekSelected(ActionEvent event) {

        AptTableView.setItems(AppointmentDAO.getAllAppointmentsAndPeopleByUpcomingWeek());
    }

    /**
     *      Changes TableView to show only Appointments in the upcoming Month
     * @param event     Action Event
     */
    public void whenUpcomingMonthSelected(ActionEvent event) {

        AptTableView.setItems(AppointmentDAO.getAllAppointmentsAndPeopleByUpcomingMonth());
    }



}
