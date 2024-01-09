package test.tdavenportc195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import test.tdavenportc195.DAO.LoginDAO;
import test.tdavenportc195.DAO.UserDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *      Log In Controller
 *
 * @author  Tyler Davenport
 */
public class LogInController implements Initializable {


    public static boolean success = false;
    public Button LogInButton;
    public Button ExitButtonLogin;
    public Label PasswordLabel;
    public Label LocationLabel;
    public Label LanguageLabel;
    public TextField UserText;
    public TextField PasswordText;
    public TextField LocationText;
    public TextField LanguageText;
    public Label UserLabel;
    @FXML
    private AnchorPane LogInPage;

    Stage stage;
    Scene scene;
    Parent root;
    private String errorMessage;


    ///-----------------------------Initialize-----------------------------------------------------///

    /**
     *          Initialized upon Loading the Login Page, Sets Location Label, Selects Language Settings
     * @param url               url
     * @param resourceBundle    Resource Bundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String location = ZoneId.systemDefault().toString();
        LocationText.setText(location);
        LocationText.setEditable(false);
        LanguageText.setEditable(false);

        resourceBundle = ResourceBundle.getBundle("test.tdavenportc195/Language", Locale.getDefault());

        try {

            if (Locale.getDefault().getLanguage().equals("fr")){

                System.out.println("Language mode set to French");

                LogInButton.setText(resourceBundle.getString("LogInButton"));
                ExitButtonLogin.setText(resourceBundle.getString("ExitButtonLogin"));

                UserLabel.setText(resourceBundle.getString("UserLabel"));
                PasswordLabel.setText(resourceBundle.getString("PasswordLabel"));
                LocationLabel.setText(resourceBundle.getString("LocationLabel"));
                LanguageLabel.setText(resourceBundle.getString("LanguageLabel"));
                LanguageText.setText(resourceBundle.getString("LanguageText"));
                errorMessage = resourceBundle.getString("ErrorMessage");

            }
            if (Locale.getDefault().getLanguage().equals("en")){

                System.out.println("Language mode set to English");

                LogInButton.setText(resourceBundle.getString("LogInButton"));
                ExitButtonLogin.setText(resourceBundle.getString("ExitButtonLogin"));

                UserLabel.setText(resourceBundle.getString("UserLabel"));
                PasswordLabel.setText(resourceBundle.getString("PasswordLabel"));
                LocationLabel.setText(resourceBundle.getString("LocationLabel"));
                LanguageLabel.setText(resourceBundle.getString("LanguageLabel"));
                LanguageText.setText(resourceBundle.getString("LanguageText"));
                errorMessage = resourceBundle.getString("ErrorMessage");

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    ///-----------------------------Login Button-----------------------------------------------------///

    /**
     *          Button to Log In to the Application, Gets the inputted username and pw and checks that it is a valid authorized user
     * @param event             Action event
     * @throws IOException      IO Exception
     * @throws SQLException     Throws SQL Exception
     */
    public void OnLoginButtonClicked (ActionEvent event) throws IOException, SQLException {

        UserDAO.getAllUsers();

        String username = UserText.getText();
        String password = PasswordText.getText();

        if (UserDAO.checkUser(username, password)) {

            System.out.println(username + " Successfully logged in!");
            success = true;
            trackLogin(username, true);

            Stage stage = (Stage) LogInButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainForm.fxml")));
            stage.setTitle("Appointments");
            stage.setScene(new Scene(root));

        }
        else {
            System.out.println(errorMessage);
            success = false;
            trackLogin(username, false);

            UserText.clear();
            PasswordText.clear();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();

        }

    }

    ///---------------------------Exit Button-------------------------------------------------------///

    /**
     *          Button to Exit the program
     * @param event     Action Event
     */
    public void OnExitButtonClicked (ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit / Sortie");
        alert.setContentText("Exit / Sortie?");


        if(alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) LogInPage.getScene().getWindow();
            System.out.println("You clicked the Exit Button");
            stage.close();
        }

    }

    ///--------------------------------Login Tracker--------------------------------------------------///

    /**
     *          Function to Track and Log all sign in attempts, Successful and Unsuccessful and write it to TXT file
     * @param username      Username of login
     * @param success       The Success or Failure to Log in
     */
    public static void trackLogin (String username, Boolean success) {
        try{

            String loginTracker = "login_activity.txt";

            FileWriter fileWriter = new FileWriter(loginTracker, true);

            if (success == true){
                String message = " Has Successfully Logged in! ";
                fileWriter.append("\n User: " + username + " ----"+ message + "---- at the time of " + LocalDateTime.now());
                System.out.println("Login success attempt tracked \n -");
                fileWriter.close();
            }
            else {
                String message = " Has Failed to Log in! ";
                fileWriter.append("\n User: " + username + " ----" + message + "---- at the time of " + LocalDateTime.now());
                System.out.println("Login failure attempt tracked \n -");
                fileWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }











}
