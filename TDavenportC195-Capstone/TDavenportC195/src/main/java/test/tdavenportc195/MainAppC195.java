package test.tdavenportc195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import test.tdavenportc195.DataBaseConnector.JDBC;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

///--------------------------------Main--------------------------------------------------///

/**
 *      Main Application
 *
 * @author Tyler Davenport
 */

public class MainAppC195 extends Application {

    /**
     *      Main
     * @param args args
     */
    public static void main(String[] args) {

        JDBC.startConnection();
        launch(args);
        JDBC.closeConnection();


    }

    /**
     *          Main Stage to Start FXML
     * @param mainStage     Stage
     * @throws IOException  IO Exception
     */
    @Override
    public void start(Stage mainStage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInPage.fxml")));
        Scene scene = new Scene(root);
        mainStage.setTitle("C195 TDavenport Appointment Management System");
        mainStage.setScene(scene);
        mainStage.show();


    }






}
