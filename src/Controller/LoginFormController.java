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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public TextField UsernameText;
    public TextField PasswordText;
    public Label ErrorLabel;
    public Label LocationLabel;
    public ZoneId localZone = ZoneId.systemDefault();

    public Label Username;
    public Label Password;
    public Button Login;
    public Button Cancel;
    public Label TimeZone;
    ResourceBundle RB = ResourceBundle.getBundle("Login", Locale.getDefault());
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    public void ToLoginButton(ActionEvent actionEvent) {
        try {
            if (DBUsers.checkLogin(UsernameText.getText(), PasswordText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(RB.getString("error"));
                alert.setContentText(RB.getString("error"));
                alert.showAndWait();
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("../view/ScheduleForm.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("SCHEDULER");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(RB.getString("error"));
            alert.setContentText(RB.getString("error"));
            alert.showAndWait();
        }
    }

    public void ToCancelButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocationLabel.setText(String.valueOf(localZone));

        Username.setText(RB.getString("username"));
        Password.setText(RB.getString("password"));
        Login.setText(RB.getString("login"));
        Cancel.setText(RB.getString("cancel"));
        TimeZone.setText(RB.getString("timezone"));

        //ErrorLabel.setText(RB.getString("error"));
    }


}
