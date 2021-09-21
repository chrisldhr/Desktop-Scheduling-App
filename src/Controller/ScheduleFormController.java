package Controller;

import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleFormController implements Initializable {
    public TableColumn AppointmentID;
    public TableColumn CustomerID;
    public TableColumn TitleColumn;
    public TableColumn TypeColumn;
    public TableColumn StartDateColumn;
    public TableColumn EndDateColumn;
    public TableView <Appointment> ScheduleTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScheduleTable.setItems(Appointment.getAllAppointments());
        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
    }


    public void ToExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void ToAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AddCustomerForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("ADD CUSTOMER FORM");
        stage.setScene(scene);
        stage.show();
    }

    public void ToModifyCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ModifyCustomerForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("MODIFY CUSTOMER FORM");
        stage.setScene(scene);
        stage.show();
    }

    public void ToDeleteCustomer(ActionEvent actionEvent) {
    }

    public void ToAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AddAppointmentForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("ADD APPOINTMENT FORM");
        stage.setScene(scene);
        stage.show();
    }

    public void ToModifyAppointment(ActionEvent actionEvent) {
    }

    public void ToDeleteAppointment(ActionEvent actionEvent) {
    }
}
