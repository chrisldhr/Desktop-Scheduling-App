package Controller;

import DBAccess.DBAppointments;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * This is the controller class for the modify appointment form
 * */

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

    public static ZoneId localZone = ZoneId.systemDefault();
    public static LocalDate date;
    public static int customerID;
    public static LocalDateTime startDateTime;
    public static LocalDateTime endDateTime;
    public static Timestamp startTimestamp;
    public static Timestamp endTimestamp;
    public static int appointmentID;

    /** When the Modify button is clicked, the appointment is saved to the database and redirects to the schedule form
     * @param actionEvent Modify button
     * @throws IOException If input values are invalid
     */
    public void ToModifyButton(ActionEvent actionEvent) throws IOException {
        try {
            String title = TitleText.getText();
            String description = DescriptionText.getText();
            String location = LocationText.getText();
            String type = TypeText.getText();
            LocalTime startTime = StartCombo.getValue();
            LocalTime endTime = EndCombo.getValue();
            int userID = UserCombo.getValue().getUserID();
            int contactID = ContactCombo.getValue().getContactID();

            appointmentID = Integer.parseInt(IDText.getText());
            date = DatePicker.getValue();
            customerID = CustomerCombo.getValue().getCustomerID();
            startDateTime = LocalDateTime.of(date, startTime);
            endDateTime = LocalDateTime.of(date, endTime);

            startTimestamp = Timestamp.valueOf(startDateTime);
            endTimestamp = Timestamp.valueOf(endDateTime);

            date = DatePicker.getValue();

            if (title.isEmpty() || description.isEmpty() || location.isEmpty() || type.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("EMPTY FIELDS");
                alert.setContentText("Please fill in all the fields");
                alert.showAndWait();
            }
            else if (startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("INVALID TIMES");
                alert.setContentText("Please choose an end time that is after the start time");
                alert.showAndWait();
            }
            else if (checkBusiness(startDateTime) || checkBusiness(endDateTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("OUTSIDE OF BUSINESS HOURS");
                alert.setContentText("Business Hours: 8:00 a.m. to 10:00 p.m. EST, including weekends");
                alert.showAndWait();
            } else if (DBAppointments.checkOverlap(customerID, startTimestamp, endTimestamp, appointmentID)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("OVERLAPPING APPOINTMENTS");
                alert.setContentText("This customer already has an appointment at the time");
                alert.showAndWait();
            } else {
                DBAppointments.modifyAppointment(appointmentID, title, description, location, type, startTimestamp, endTimestamp, customerID, userID, contactID);

                Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("SCHEDULER");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("EMPTY FIELDS");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        }
    }

    /** When the cancel button is clicked, the view redirects to the schedule form
     * @param actionEvent Cancel button
     * @throws IOException From FXMLLoader.
     */
    public void ToCancelButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
    }

    /** Initializes the form with selected appointment values
     * @param url The url used to resolve relative paths
     * @param resourceBundle The resourceBundle used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Selected = ScheduleFormController.getModifyingAppointment();

        ContactCombo.setItems(DBContacts.getAllContacts());
        CustomerCombo.setItems(DBCustomers.getAllCustomers());
        UserCombo.setItems(DBUsers.getAllUsers());

        ContactCombo.setVisibleRowCount(5);
        CustomerCombo.setVisibleRowCount(5);
        UserCombo.setVisibleRowCount(5);

        LocalTime start = LocalTime.of(0,0);
        LocalTime end = LocalTime.of(23,30);

        do {
            StartCombo.getItems().add(start);
            EndCombo.getItems().add(start);
            start = start.plusHours(1);
        } while(start.minusMinutes(1).isBefore(end));

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

    /** This method checks that the time is outside of business hours
     * @param time the time to check
     */
    public Boolean checkBusiness(LocalDateTime time) {
        LocalDateTime startBusiness = ToLocal.Convert(LocalDateTime.of(date, LocalTime.of(8, 0)));
        LocalDateTime endBusiness = ToLocal.Convert(LocalDateTime.of(date, LocalTime.of(22, 0)));

        return time.isBefore(startBusiness) || time.isAfter(endBusiness);
    }

    /** This is the Lambda expression that converts EST to local time
     * and is used in the checkBusiness function
     */
    ConvertInterface ToLocal = T -> {
        return T.atZone(ZoneId.of("America/New_York")).withZoneSameInstant(localZone).toLocalDateTime();
    };

}
