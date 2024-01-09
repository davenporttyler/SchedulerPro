module test.tdavenportc195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens test.tdavenportc195 to javafx.fxml;
    exports test.tdavenportc195;

    opens test.tdavenportc195.DAO to javafx.fxml;
    exports test.tdavenportc195.DAO;

    opens test.tdavenportc195.Models to javafx.fxml;
    exports test.tdavenportc195.Models;

    opens test.tdavenportc195.DataBaseConnector to javafx.fxml;
    exports test.tdavenportc195.DataBaseConnector;
}