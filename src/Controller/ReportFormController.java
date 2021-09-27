package Controller;

import DBAccess.DBAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {
    public ComboBox MonthCombo;
    public ComboBox TypeCombo;
    public TextField TotalText;
    public TableColumn AppointmentID;
    public TableColumn Title;
    public TableColumn Description;
    public TableColumn Location;
    public TableColumn Type;
    public TableColumn Start;
    public TableColumn End;
    public TableColumn ApptCustomerID;
    public TableColumn UserID;
    public TableColumn ContactID;
    public TableColumn CustomerID;
    public TableColumn Name;
    public TableColumn Address;
    public TableColumn Postal;
    public TableColumn Phone;
    public TableColumn DivisionID;
    public ObservableList<String> months;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        MonthCombo.setItems(months);
        TypeCombo.setItems(DBAppointments.getAllTypes());
    }

    public void ToScheduleForm(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
    }

    public void ToRunButton(ActionEvent actionEvent) {
        String month = (String) MonthCombo.getValue();
        String type = (String) TypeCombo.getValue();

        String total = String.valueOf(DBAppointments.getTotalAppts(month, type));
        TotalText.setText(total);
    }

    public void ToContactCombo(ActionEvent actionEvent) {
    }

    public void ToDivisionIDCombo(ActionEvent actionEvent) {
    }


}
