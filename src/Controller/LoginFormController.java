package Controller;

import DBAccess.DBUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is the controller class for the login form
 * */
public class LoginFormController implements Initializable {
    public TextField UsernameText;
    public TextField PasswordText;
    public Label LocationLabel;
    public ZoneId localZone = ZoneId.systemDefault();

    public Label Username;
    public Label Password;
    public Button Login;
    public Button Cancel;
    public Label TimeZone;
    ResourceBundle RB = ResourceBundle.getBundle("Login", Locale.getDefault());
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    /** When the Login button is clicked, the username and password is compared to the database and if matches, redirects to the schedule form
     * @param actionEvent Login button
     * @throws IOException If input values are invalid
     */
    public void ToLoginButton(ActionEvent actionEvent) throws IOException {
        try {
            if (DBUsers.checkLogin(UsernameText.getText(), PasswordText.getText())) {
                loginAttempt("Failed");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(RB.getString("error"));
                alert.setContentText(RB.getString("error"));
                alert.showAndWait();
            } else {
                loginAttempt("Successful");
                Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("SCHEDULER");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (Exception e) {
            loginAttempt("Failed");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(RB.getString("error"));
            alert.setContentText(RB.getString("error"));
            alert.showAndWait();
        }
    }

    /** When the cancel button is clicked, the program exits
     * @param actionEvent Cancel button
     */
    public void ToCancelButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    /** Initializes the form with values of default language
     * @param url The url used to resolve relative paths
     * @param resourceBundle The resourceBundle used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocationLabel.setText(String.valueOf(localZone));

        Username.setText(RB.getString("username"));
        Password.setText(RB.getString("password"));
        Login.setText(RB.getString("login"));
        Cancel.setText(RB.getString("cancel"));
        TimeZone.setText(RB.getString("timezone"));
    }

    /** This method tracks login attempts to a text file
     * @param attempt the login attempt
     */
    public void loginAttempt(String attempt) throws IOException {
        String filename = "login_activity.txt";
        FileWriter fwriter = new FileWriter(filename, true);

        PrintWriter outputFile = new PrintWriter(fwriter);

        outputFile.println("USER: " + UsernameText.getText() + " DATE/TIME: " + LocalDateTime.now() + " LOGIN: " + attempt);
        outputFile.close();
    }

}
