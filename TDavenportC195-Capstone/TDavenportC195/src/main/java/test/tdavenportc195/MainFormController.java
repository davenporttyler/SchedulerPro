package test.tdavenportc195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import test.tdavenportc195.DAO.AppointmentDAO;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 *       Main Form Controller / Home Screen
 *
 * @author Tyler Davenport
 */
public class MainFormController implements Initializable {


    public Button ExitButtonMain;
    public Button AppointmentsButtonMain;
    public Button CustomersButtonMain;
    public Button ReportsButtonMain;
    public Label AppointmentLabelMain;
    @FXML
    private AnchorPane MainForm;

    Stage stage;
    Scene scene;
    Parent root;


    ///------------------------------Initialize----------------------------------------------------///

    /**
     *          Initialized upon loading Main From / Home Screen of the program, Checks if there are any upcoming appointments and sets label
     * @param url               url
     * @param resourceBundle    Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if (AppointmentDAO.getAllLoginAppointments().contains("Start: null") ){

            AppointmentLabelMain.setText("No Upcoming Appointments");

        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Appointment Reminder");
            alert.setHeaderText("Appointment Reminder Warning");
            alert.setContentText("You have an appointment soon!");
            alert.showAndWait();

            AppointmentLabelMain.setText(AppointmentDAO.getAllLoginAppointments());

        }






    }
    ///-------------------------------Exit Button---------------------------------------------------///

    /**
     *          Button to Exit the Program
     * @param event         Action Event
     * @throws IOException  IO Exception
     */

    public void OnExitButtonMainClicked (ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit Appointment Management System?");
        alert.setContentText("Press 'OK' to close the Appointment Management System OR 'Cancel' to stay inside.");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) MainForm.getScene().getWindow();
            System.out.println("You clicked the Exit Button");
            stage.close();
        }
    }




    ///-------------------------------Appointments Button---------------------------------------------------///

    /**
     *          Button to load the Appointments Screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnAppointmentsButtonMainClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage) AppointmentsButtonMain.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AppointmentsMain.fxml")));
        stage.setTitle("Appointments");
        stage.setScene(new Scene(root));

    }

    ///-------------------------------Customers Button---------------------------------------------------///

    /**
     *          Button to load the Customers Screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnCustomersButtonMainClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage) CustomersButtonMain.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersMain.fxml")));
        stage.setTitle("Customers");
        stage.setScene(new Scene(root));

    }


    ///-------------------------------Reports Button---------------------------------------------------///

    /**
     *          Button to load the Reports Screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnReportsButtonMainClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage) ReportsButtonMain.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ReportsMain.fxml")));
        stage.setTitle("Reports");
        stage.setScene(new Scene(root));

    }




}
