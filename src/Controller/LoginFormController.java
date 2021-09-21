package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginFormController {
    public TextField UsernameText;
    public TextField PasswordText;
    public Label ErrorLabel;
    public Label LocationLabel;


    public void ToLoginButton(ActionEvent actionEvent) {
    }

    public void ToCancelButton(ActionEvent actionEvent) {
        System.exit(0);
    }
}
