package Controller;

import DBAccess.DBContacts;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentFormController implements Initializable {


    public TextField IDText;
    public TextField TypeText;
    public TextField TitleText;
    public TextField DescriptionText;
    public TextField LocationText;
    public ComboBox ContactCombo;
    public javafx.scene.control.DatePicker DatePicker;
    public ComboBox StartCombo;
    public ComboBox EndCombo;
    public ComboBox CustomerCombo;
    public ComboBox UserCombo;

    public void ToAddButton(ActionEvent actionEvent) {
    }

    public void ToCancelButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ScheduleForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContactCombo.setItems(DBContacts.getAllContacts());
        CustomerCombo.setItems(DBCustomers.getAllCustomers());
        UserCombo.setItems(DBUsers.getAllUsers());

        ContactCombo.setVisibleRowCount(5);
        CustomerCombo.setVisibleRowCount(5);
        UserCombo.setVisibleRowCount(5);
    }
}
