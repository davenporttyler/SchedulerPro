package test.tdavenportc195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import test.tdavenportc195.Models.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *      Customers Main Controller
 *
 * @author Tyler Davenport
 */
public class CustomersMainController implements Initializable {


    public TableColumn CustDivisionIDCol;
    public Button searchBtn;
    public TextField searchField;
    @FXML
    private Button AddCustButton;
    @FXML
    private TableColumn<?, ?> CustAddressCol;
    @FXML
    private TableColumn<?, ?> CustCountryCol;
    @FXML
    private TableColumn<?, ?> CustDivisionCol;
    @FXML
    private TableColumn<?, ?> CustIDCol;
    @FXML
    private Button CustMainHomeButton;
    @FXML
    private TableColumn<?, ?> CustNameCol;
    @FXML
    private TableColumn<?, ?> CustPhoneCol;
    @FXML
    private TableColumn<?, ?> CustPostalCol;
    @FXML
    private TableView<Customers> CustTableView;
    @FXML
    private Button DeleteCustButton;
    @FXML
    private Button ModCustButton;
    @FXML
    private TextField SearchBarCustMain;
    @FXML
    private Button SearchBtnCustMain;

    ///-------------------------Initialize---------------------------------------------------------///

    /**
     *      Initialized upon loading Customers Main page
     * @param url               url
     * @param resourceBundle    Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        CustIDCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        CustNameCol.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        CustAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CustPostalCol.setCellValueFactory(new PropertyValueFactory<>("postal_Code"));
        CustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        CustDivisionIDCol.setCellValueFactory(new PropertyValueFactory<>("division_ID"));
        CustDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        CustCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));


        CustTableView.setItems(CustomerDAO.getAllCustomers());

    }

    ///--------------------------------------Search Function--------------------------------///

    private ObservableList<Customers> customerSearch (String tempSearch){

        ObservableList<Customers> searchedCust = FXCollections.observableArrayList();

        ObservableList<Customers> allCust = CustomerDAO.getAllCustomers();

        for (Customers cust : allCust) {
            if (cust.getCustomer_Name().contains(tempSearch) ){
                searchedCust.add(cust);
            }
        }
        return searchedCust;

    }

    @FXML public void OnCustomerSearchButtonClicked (ActionEvent event) {

        String s = searchField.getText();

        ObservableList<Customers> searched = customerSearch(s);

        CustTableView.setItems(searched);
        searchField.setText("");

        if (searched.size() == 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("No Results Found!");
            alert.setContentText("No Customers with those letter in their name were found!");
            alert.showAndWait();
        }

    }




    ///---------------------------Add Customer Button-------------------------------------------------------///

    /**
     *          Button to go to the Add Customer Screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnAddCustomerButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  AddCustButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersAdd.fxml")));
        stage.setTitle("C195 TDavenport Add Customer");
        stage.setScene(new Scene(root));
        stage.show();

    }
    ///---------------------------Modify Customer-------------------------------------------------------///

    /**
     *          Button to go to the Modify Customer screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     * @throws SQLException Throws SQL Exception
     */
    public void OnModCustButtonClicked (ActionEvent event) throws IOException, SQLException {


        if (CustTableView.getSelectionModel().getSelectedItem() == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Error");
            alert.setContentText("Please Confirm: You must select an Customer to Modify!");
            alert.showAndWait();

        }
        else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomersMod.fxml"));
            Parent root = loader.load();

            Scene customerModScene = new Scene(root);

            CustomersModController customersModController = loader.getController();
            customersModController.loadSelectedCustomer(CustTableView.getSelectionModel().getSelectedItem());

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(customerModScene);
            window.show();

        }
    }

    ///------------------------------Delete Customer----------------------------------------------------///

    /**
     *      Button to delete the selected Customer
     * @param event         Action Event
     * @throws IOException  IO Exception
     * @throws SQLException Throws SQL Exception
     */
    public void OnDeleteCustClicked(ActionEvent event) throws IOException, SQLException {


        if (CustTableView.getSelectionModel().getSelectedItem() == null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Error");
            alert.setContentText("Please Confirm: You must select an Customer to Delete!");
            alert.showAndWait();

        }
        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Confirm Delete");
            alert.setContentText("Please Confirm You Want to Delete Customer");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK){

                int Customer_ID = CustTableView.getSelectionModel().getSelectedItem().getCustomer_ID();

                try {

                    AppointmentDAO.deleteSelectedAppointmentCustomer(Customer_ID);

                }
                catch (SQLException e){
                    e.printStackTrace();
                }

                int success = CustomerDAO.deleteSelectedCustomer(Customer_ID);

                if (success > 0 ){

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Customer Deleted");
                    alert2.setHeaderText("Customer Deleted");
                    alert2.setContentText("Selected Customer has been successfully deleted.");
                    alert2.showAndWait();

                    CustTableView.setItems(CustomerDAO.getAllCustomers());
                    System.out.println("Customer with ID#: " + Customer_ID + " Successfully Deleted");
                }
                else {
                    System.out.println("Customer not found");
                }
            }
        }


    }
    ///-------------------------------Home Button---------------------------------------------------///

    /**
     *      Button to go back to the Home Screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnHomeButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  CustMainHomeButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }







}
