package test.tdavenportc195;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import test.tdavenportc195.DataBaseConnector.JDBC;
import test.tdavenportc195.Models.Customers;

//import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 *      Modify Customers Controller
 *
 * @author Tyler Davenport
 */
public class CustomersModController implements Initializable {

    private static Customers modCustomer = null;
    //private static Label CustIDMod;
    public Button SaveModCustBtn;
    public Button ModCustBackButton;
    public TextField CustIDMod;
    public TextField CustNameMod;
    public TextField CustAddressMod;
    public TextField CustPostalMod;
    public TextField CustPhoneMod;
    public ComboBox CustCountryMod;
    public ComboBox CustStateMod;
    public ComboBox CustDivisionMod;
    public TextField CustDivisionSet;
    public ComboBox CustCountryModCombo;
    public ComboBox CustDivisionModCombo;

    private  int baseIndex = 0;


    ///---------------------------Initialize-------------------------------------------------------///



    private Customers selectedCustomer;
    private String selectedCountry = "selectedCountry";

    /**
     *      Loads the selected customer from the Customer TableView and inputs sets the fields
     * @param customers         Selected Customer
     * @throws SQLException     Throws SQL Exception
     */
    public void loadSelectedCustomer(Customers customers) throws SQLException {

        selectedCustomer = customers;

        CustIDMod.setText(String.valueOf(selectedCustomer.getCustomer_ID()));
        CustNameMod.setText(selectedCustomer.getCustomer_Name());
        CustAddressMod.setText(selectedCustomer.getAddress());
        CustPostalMod.setText(String.valueOf(selectedCustomer.getPostal_Code()));
        CustPhoneMod.setText(String.valueOf(selectedCustomer.getPhone()));
        CustCountryModCombo.setValue(selectedCustomer.getCountry());
        CustDivisionModCombo.setValue(selectedCustomer.getDivision());
        CustDivisionSet.setText(String.valueOf(selectedCustomer.getDivision_ID()));

        selectedCountry = CustCountryModCombo.getValue().toString();


    }

    /**
     *          Initialized upon loading the Modify Customers
     * @param url               url
     * @param resourceBundle    Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            initializeCountry();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    ///---------------------------Save Button-------------------------------------------------------///

    /**
     *          Button to Save the Modified Customer
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnSaveModCustomerButtonClicked (ActionEvent event) throws IOException {

        if (
                CustDivisionSet.getText().isEmpty() ||
                CustIDMod.getText().isEmpty() ||
                CustPhoneMod.getText().isEmpty() ||
                CustPostalMod.getText().isEmpty() ||
                CustAddressMod.getText().isEmpty() ||
                CustNameMod.getText().isEmpty()
        ){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Missing Info");
            alert.setHeaderText("You have missing info!");
            alert.setContentText("One of more field are missing information. Please check entries and try again!");
            alert.showAndWait();
            return;

        }

        try {
            //sql Select Statement
            String sqlInsert = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?\n" +
                    " WHERE Customer_ID = ?";

            //See If statement is normal
            //System.out.println(sqlInsert);

            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlInsert);

            prepStatement.setString(1, CustNameMod.getText());
            prepStatement.setString(2, CustAddressMod.getText());
            prepStatement.setString(3, CustPostalMod.getText());
            prepStatement.setString(4, CustPhoneMod.getText());
            prepStatement.setInt(5, Integer.parseInt(CustDivisionSet.getText()));
            prepStatement.setInt(6, Integer.parseInt(CustIDMod.getText()));

            prepStatement.execute();

        }

        catch (SQLException e){

            e.printStackTrace();
        }


        Stage stage = (Stage)  SaveModCustBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersMain.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }

    ///---------------------------Back Button-------------------------------------------------------///

    /**
     *          Button to go back to the Main Customer Screen
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnCustomersBackButtonClicked (ActionEvent event) throws IOException {

        Stage stage = (Stage)  ModCustBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersMain.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }


    ///-------------------------------------------------------------------------------------------------///

    /**
     *      Loads the Country Combo box with all countries
     * @throws SQLException     Throws SQL Exception
     */
    private void initializeCountry() throws SQLException {

        //sql Select Statement
        String sqlSelect = "SELECT Country FROM countries";
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        ResultSet resSet = prepStatement.executeQuery();

        while (resSet.next()) {
            CustCountryModCombo.getItems().add(resSet.getString(1));

        }

    }

    ///-----------------------------------------------------------------------------------------------///

    /**
     *          When the Country Combo box is re-selected during Modify, it resets the Division combo and label to allow a new selection
     * @param event         Action Event
     * @throws SQLException Throws SQL Exception
     */

    public void whenCountryComboSelected(ActionEvent event) throws SQLException {

        CustDivisionModCombo.getItems().clear();
        CustDivisionModCombo.setPromptText(null);
        CustDivisionModCombo.setValue(null);

        CustDivisionSet.clear();

        String countrySelected = null;
        int countryID = 0;

        countrySelected = CustCountryModCombo.getSelectionModel().getSelectedItem().toString();
        //System.out.println(countrySelected);

        if (countrySelected.equals("U.S")){
            countryID = 1;
        }
        if (countrySelected.equals("UK")){
            countryID = 2;
        }
        if (countrySelected.equals("Canada")){
            countryID = 3;
        }
        CustDivisionModCombo.setPromptText("Select Division");
        //sql Select Statement
        String sqlSelect = "SELECT Division FROM first_level_divisions WHERE Country_ID = "+ countryID ;
        //System.out.println(sqlSelect);
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                CustDivisionModCombo.getItems().add(resSet.getString(1));

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }




    ///---------------------------------------------------------------------------------------------///

    /**
     *          When a Division is selected from the Combo box, the correlated Division ID is set to a label
     * @param event             Action Event
     * @throws SQLException     Throws SQL Exception
     */
    public void whenDivisionComboSelected(ActionEvent event) throws SQLException{

        String divisionName = (String) CustDivisionModCombo.getSelectionModel().getSelectedItem();

        //sql Select Statement
        String sqlSelect = "SELECT Division_ID FROM first_level_divisions WHERE Division = '" + divisionName + "'";
        //Test
        //System.out.println(divisionName);
        //System.out.println(sqlSelect);
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                CustDivisionSet.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
