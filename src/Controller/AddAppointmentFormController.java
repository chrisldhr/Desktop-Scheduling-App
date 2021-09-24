package Controller;

import DBAccess.*;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentFormController implements Initializable {


    public TextField IDText;
    public TextField TypeText;
    public TextField TitleText;
    public TextField DescriptionText;
    public TextField LocationText;
    public ComboBox<Contact> ContactCombo;
    public javafx.scene.control.DatePicker DatePicker;
    public ComboBox<LocalTime> StartCombo;
    public ComboBox<LocalTime> EndCombo;
    public ComboBox<Customer> CustomerCombo;
    public ComboBox<User> UserCombo;
    //private static ObservableList allTimes = FXCollections.observableArrayList();

    public void ToAddButton(ActionEvent actionEvent) throws IOException {
        String title = TitleText.getText();
        String description = DescriptionText.getText();
        String location = LocationText.getText();
        String type = TypeText.getText();
        LocalTime startTime = StartCombo.getValue();
        LocalTime endTime = EndCombo.getValue();
        int customerID = CustomerCombo.getValue().getCustomerID();
        int userID = UserCombo.getValue().getUserID();
        int contactID = ContactCombo.getValue().getContactID();

        LocalDate date = DatePicker.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);

        Timestamp startSQL = Timestamp.valueOf(startDateTime);
        Timestamp endSQL = Timestamp.valueOf(endDateTime);

        DBAppointments.addAppointment(title, description, location, type, startSQL, endSQL, customerID, userID, contactID);

        Parent root = FXMLLoader.load(getClass().getResource("../view/ScheduleForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
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
