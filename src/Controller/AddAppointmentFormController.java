package Controller;

import DBAccess.*;
import Model.Contact;
import Model.Customer;
import Model.User;
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
 * This is the controller class for the add appointment form
 * */

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

    public static ZoneId localZone = ZoneId.systemDefault();
    public static LocalDate date;
    public static int customerID;
    public static LocalDateTime startDateTime;
    public static LocalDateTime endDateTime;
    public static Timestamp startTimestamp;
    public static Timestamp endTimestamp;

    /** When the Add button is clicked, the appointment is saved to the database and redirects to the schedule form
     * @param actionEvent Add button
     * @throws IOException If input values are invalid
     */
    public void ToAddButton(ActionEvent actionEvent) throws IOException {
        try {
            String title = TitleText.getText();
            String description = DescriptionText.getText();
            String location = LocationText.getText();
            String type = TypeText.getText();
            LocalTime startTime = StartCombo.getValue();
            LocalTime endTime = EndCombo.getValue();
            int userID = UserCombo.getValue().getUserID();
            int contactID = ContactCombo.getValue().getContactID();

            date = DatePicker.getValue();
            customerID = CustomerCombo.getValue().getCustomerID();
            startDateTime = LocalDateTime.of(date, startTime);
            endDateTime = LocalDateTime.of(date, endTime);

            startTimestamp = Timestamp.valueOf(startDateTime);
            endTimestamp = Timestamp.valueOf(endDateTime);

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
            } else if (DBAppointments.checkOverlap(customerID, startTimestamp, endTimestamp, 0)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("OVERLAPPING APPOINTMENTS");
                alert.setContentText("This customer already has an appointment at the time");
                alert.showAndWait();
            } else {
                DBAppointments.addAppointment(title, description, location, type, startTimestamp, endTimestamp, customerID, userID, contactID);

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

    /** Initializes the form with times and values from contact, customer, user tables
     * @param url The url used to resolve relative paths
     * @param resourceBundle The resourceBundle used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

//        StartCombo.getSelectionModel().select(LocalTime.of(8,0));
//        EndCombo.getSelectionModel().select(LocalTime.of(9,0));

        StartCombo.setVisibleRowCount(5);
        EndCombo.setVisibleRowCount(5);

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

