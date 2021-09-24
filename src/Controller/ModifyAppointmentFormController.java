package Controller;

import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.*;
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

public class ModifyAppointmentFormController implements Initializable {
    public Appointment Selected;
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

    public void ToModifyButton(ActionEvent actionEvent) {

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
        Selected = ScheduleFormController.getModifyingAppointment();

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

        DatePicker.setValue(Selected.getStart().toLocalDateTime().toLocalDate());
        StartCombo.getSelectionModel().select(Selected.getStart().toLocalDateTime().toLocalTime());
        EndCombo.getSelectionModel().select(Selected.getEnd().toLocalDateTime().toLocalTime());

        StartCombo.setVisibleRowCount(5);
        EndCombo.setVisibleRowCount(5);

        IDText.setText(String.valueOf(Selected.getAppointmentID()));
        TypeText.setText(Selected.getType());
        TitleText.setText(Selected.getTitle());
        DescriptionText.setText(Selected.getDescription());
        LocationText.setText(Selected.getLocation());

        for (int i=0; i < CustomerCombo.getItems().size(); i++) {
            Customer customer = CustomerCombo.getItems().get(i);
            if (customer.getCustomerID() == Selected.getCustomerID()) {
                CustomerCombo.setValue(customer);
                break;
            }
        }

        for (int i=0; i < ContactCombo.getItems().size(); i++) {
            Contact contact = ContactCombo.getItems().get(i);
            if (contact.getContactID() == Selected.getContactID()) {
                ContactCombo.setValue(contact);
                break;
            }
        }

        for (int i=0; i < UserCombo.getItems().size(); i++) {
            User user = UserCombo.getItems().get(i);
            if (user.getUserID() == Selected.getUserID()) {
                UserCombo.setValue(user);
                break;
            }
        }
    }
}
