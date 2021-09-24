package Controller;

import DBAccess.DBContacts;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalTime;
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
    private static ObservableList allTimes = FXCollections.observableArrayList();


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

        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(22,0);

        while(start.isBefore(end)) {
            StartCombo.getItems().add(start);
            EndCombo.getItems().add(start.plusHours(1));
            start = start.plusHours(1);
        }

        StartCombo.getSelectionModel().select(LocalTime.of(8,0));
        EndCombo.getSelectionModel().select(LocalTime.of(9,0));

        StartCombo.setVisibleRowCount(5);
        EndCombo.setVisibleRowCount(5);

    }
}
