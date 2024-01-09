package test.tdavenportc195;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import test.tdavenportc195.DAO.ReportDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 *      Reports Main Controller
 *
 * @author Tyler Davenport
 */
public class ReportsMainController implements Initializable {

    public Button ReportsMainHomeButton;

    public TableView report3TableView;
    public TableColumn countryIDColTab3;
    public TableColumn countryNameColTab3;
    public TableColumn divisionColTab3;
    public TableColumn numberColTab3;

    public TableView report1TableView;
    public TableColumn typeColTab1;
    public TableColumn monthColTab1;
    public TableColumn numberColTab1;

    public TableView report2TableView;
    public TableColumn contactIDColTab2;
    public TableColumn ContactNameColTab2;
    public TableColumn apptIDColTab2;
    public TableColumn titleColTab2;
    public TableColumn typeColTab2;
    public TableColumn descColTab2;
    public TableColumn startColTab2;
    public TableColumn endColTab2;
    public TableColumn custIDColTab2;
    public TableColumn custNameColTab2;
    public Label timeStampLbl;


    ///--------------------------------Initialize--------------------------------------------------///

    /**
     *          Initialized upon loading the Reports Screen
     * @param url               url
     * @param resourceBundle    Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeColTab1.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthColTab1.setCellValueFactory(new PropertyValueFactory<>("monthName"));
        numberColTab1.setCellValueFactory(new PropertyValueFactory<>("count"));

        report1TableView.setItems(ReportDAO.getReport1());

        contactIDColTab2.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
        ContactNameColTab2.setCellValueFactory(new PropertyValueFactory<>("contact_name"));
        apptIDColTab2.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        titleColTab2.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColTab2.setCellValueFactory(new PropertyValueFactory<>("type"));
        descColTab2.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColTab2.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColTab2.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIDColTab2.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        custNameColTab2.setCellValueFactory(new PropertyValueFactory<>("customer_name"));

        report2TableView.setItems(ReportDAO.getReport2());

        countryNameColTab3.setCellValueFactory(new PropertyValueFactory<>("country"));
        divisionColTab3.setCellValueFactory(new PropertyValueFactory<>("division"));
        numberColTab3.setCellValueFactory(new PropertyValueFactory<>("customerCount"));

        report3TableView.setItems(ReportDAO.getReport3());


        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss"));
        timeStampLbl.setText(time);

    }

    ///--------------------------------Home Button--------------------------------------------------///

    /**
     *          Button to go back to the Home Screen
     * @param event
     * @throws IOException
     */
    public void OnHomeButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  ReportsMainHomeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }












}
