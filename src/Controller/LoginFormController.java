package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField UsernameText;
    public TextField PasswordText;
    public Label ErrorLabel;
    public Label LocationLabel;


    public void ToLoginButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ScheduleForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
    }

    public void ToCancelButton(ActionEvent actionEvent) {
        System.exit(0);
    }
}
