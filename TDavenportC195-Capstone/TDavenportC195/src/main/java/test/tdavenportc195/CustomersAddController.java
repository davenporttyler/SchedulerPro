package test.tdavenportc195;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import test.tdavenportc195.DataBaseConnector.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;


/**
 *          Add Customers Controller
 *
 * @author Tyler Davenport
 */
public class CustomersAddController implements Initializable {


    public Button SaveAddCustBtn;
    public Button AddCustBackButton;
    public TextField CustIDAdd;
    public TextField CustNameAdd;
    public TextField CustAddressAdd;
    public TextField CustPostalAdd;
    public TextField CustPhoneAdd;
    public ComboBox CustCountryAddCombo;
    public ComboBox CustDivisionAddCombo;
    public Label DivisionLabel;
    public TextField CustDivisionSetAdd;

    int randomID = 0;
    int minRandom = (int) (Math.random()*100);
    int maxRandom = (int) (Math.random()*200);



    ///-----------------------Initialize-----------------------------------------------------------///

    /**
     *      Initialized upon loading the Add Customers Page
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

        try {
            //Generate random number as upper and lower bounds to add to Java's Math.random to make ID more Unique
            Random random1 = new Random();
            int randomNumber1 = random1.nextInt((int) ((minRandom) * (Math.random() * 111) + 1));
            Random random2 = new Random();
            int randomNumber2 = random2.nextInt((int) ((maxRandom) * (Math.random() * 666) + 1));

            //Randomise with Java's Math.random using upper and lower random bound
            randomID = (int)(Math.random() * 100) + ((randomNumber1 + randomNumber2) * 69);
            //Auto Set ID field to random number on Add Part screen launch
            CustIDAdd.setText(String.valueOf(randomID));
            CustIDAdd.setEditable(false);
        }catch (Exception e){
            e.printStackTrace();

        }


    }

    ///------------------------------------------------------------------------------------------------///

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
            CustCountryAddCombo.getItems().add(resSet.getString(1));

        }

    }


    ///-----------------------Save Added Customer-----------------------------------------------------------///

    /**
     *      Button to Save the Customer and Add to Database
     * @param event         Action Event
     * @throws IOException  IO Exception
     */
    public void OnSaveAddCustomerButtonClicked(ActionEvent event) throws IOException {

        if (
                CustDivisionSetAdd.getText().isEmpty() ||
                CustIDAdd.getText().isEmpty() ||
                CustPhoneAdd.getText().isEmpty() ||
                CustPostalAdd.getText().isEmpty() ||
                CustAddressAdd.getText().isEmpty() ||
                CustNameAdd.getText().isEmpty()
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
            String sqlInsert = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, " +
                    "Phone, Division_ID) VALUES (?,?,?,?,?,?)";

            //See If statement is normal
            //System.out.println(sqlInsert);

            PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlInsert);

            prepStatement.setInt(1, Integer.parseInt(CustIDAdd.getText()));
            prepStatement.setString(2, CustNameAdd.getText());
            prepStatement.setString(3, CustAddressAdd.getText());
            prepStatement.setString(4, CustPostalAdd.getText());
            prepStatement.setString(5, CustPhoneAdd.getText());
            prepStatement.setInt(6, Integer.parseInt(CustDivisionSetAdd.getText()));

            prepStatement.execute();

        }
        catch (SQLException e){

            e.printStackTrace();
        }


        Stage stage = (Stage) SaveAddCustBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersMain.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }

    ///-------------------------Back Button---------------------------------------------------------///

    /**
     *          Button to go Back to Customer Main screen
     * @param event             Action Event
     * @throws IOException      IO Exception
     */
    public void OnCustomerBackButtonClicked(ActionEvent event) throws IOException {

        Stage stage = (Stage) AddCustBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CustomersMain.fxml")));
        stage.setTitle("C195 TDavenport Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();

    }

    ///-------------------------------------------------------------------------------------------------///

    /**
     *          When the a Country is selected from the combo box, The Division combo box is filled with the results of a division query
     * @param event             Action Event
     * @throws SQLException     Throws SQL Exception
     */
    public void whenCountryComboSelected(ActionEvent event) throws SQLException {

        CustDivisionAddCombo.getItems().clear();
        CustDivisionSetAdd.clear();

        String countrySelected = null;
        int countryID = 0;

        countrySelected = CustCountryAddCombo.getSelectionModel().getSelectedItem().toString();
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
        //sql Select Statement
        String sqlSelect = "SELECT Division FROM first_level_divisions WHERE Country_ID = "+ countryID ;
        //System.out.println(sqlSelect);
        //Prepared Statement
        PreparedStatement prepStatement = JDBC.getConnection().prepareStatement(sqlSelect);
        //ResultSet
        try (ResultSet resSet = prepStatement.executeQuery()) {

            while (resSet.next()) {
                CustDivisionAddCombo.getItems().add(resSet.getString(1));

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    ///--------------------------------------------------------------------------------------------------///

    /**
     *          When a Division is selected from the Combo box, the correlated Division ID is set to a label
     * @param event             Action Event
     * @throws SQLException     Throws SQL Exception
     */
    public void whenDivisionComboSelected(ActionEvent event) throws SQLException {

        String divisionName = (String) CustDivisionAddCombo.getSelectionModel().getSelectedItem();

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
                CustDivisionSetAdd.setText(resSet.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





}
