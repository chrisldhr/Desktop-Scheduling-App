package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Model.Appointment;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;

/**
 * This is the controller class for the schedule form
 * */
public class ScheduleFormController implements Initializable {
    private static Customer modifyingCustomer;
    private static Appointment modifyingAppointment;
    public TableView <Appointment> AppointmentTable;
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
    public TableView <Customer> CustomerTable;
    public TableColumn CustomerID;
    public TableColumn Name;
    public TableColumn Address;
    public TableColumn Postal;
    public TableColumn Phone;
    public TableColumn DivisionID;
    public Label RemoveCustomerLabel;
    public Label RemoveAppointmentLabel;
    public Label UpcomingAlert;

    /** This method returns the selected customer to modify
     * @return the modifyingCustomer
     */
    public static Customer getModifyingCustomer() {
        return modifyingCustomer;
    }

    /** This method returns the selected appointment to modify
     * @return the modifyingAppointment
     */
    public static Appointment getModifyingAppointment() { return modifyingAppointment; }

    /** Initializes the form table views with values from the database
     * @param url The url used to resolve relative paths
     * @param resourceBundle The resourceBundle used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkUpcoming();

        AppointmentTable.setItems(DBAppointments.getAllAppointments());
        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        ApptCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        ContactID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));

        CustomerTable.setItems(DBCustomers.getAllCustomers());
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Postal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        DivisionID.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));

    }

    /** When the Exit button is clicked, the program exits
     * @param actionEvent Exit button
     */
    public void ToExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    /** When the Add Customer button is clicked, the view redirects to the add customer form
     * @param actionEvent Add Customer button
     * @throws IOException From FXMLLoader.
     */
    public void ToAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCustomerForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("ADD CUSTOMER FORM");
        stage.setScene(scene);
        stage.show();
    }

    /** When the Modify Customer button is clicked, the view redirects to the modify customer form
     * @param actionEvent Modify Customer button
     * @throws IOException From FXMLLoader.
     */
    public void ToModifyCustomer(ActionEvent actionEvent) throws IOException {
        modifyingCustomer = CustomerTable.getSelectionModel().getSelectedItem();

        if (modifyingCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No customer selected");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyCustomerForm.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("MODIFY CUSTOMER FORM");
            stage.setScene(scene);
            stage.show();
        }
    }

    /** When the Delete Customer button is clicked, customer and associated appointments are deleted
     * @param actionEvent Delete Customer button
     * @throws IOException From FXMLLoader.
     */
    public void ToDeleteCustomer(ActionEvent actionEvent) {
        modifyingCustomer = CustomerTable.getSelectionModel().getSelectedItem();

        if (modifyingCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No customer selected");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
        }
        else {
            int customerID = modifyingCustomer.getCustomerID();

            DBCustomers.deleteCustomer(customerID);

            CustomerTable.setItems(DBCustomers.getAllCustomers());
            CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
            Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
            Postal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
            Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            DivisionID.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));

            RemoveCustomerLabel.setText("Customer Removed");
            RemoveCustomerLabel.setTextFill(RED);

            AppointmentTable.setItems(DBAppointments.getAllAppointments());
            AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
            Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
            Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
            End.setCellValueFactory(new PropertyValueFactory<>("End"));
            ApptCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
            UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            ContactID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        }
    }

    /** When the Add Appointment button is clicked, the view redirects to the Add Appointment form
     * @param actionEvent Add Appointment button
     * @throws IOException From FXMLLoader.
     */
    public void ToAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddAppointmentForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("ADD APPOINTMENT FORM");
        stage.setScene(scene);
        stage.show();
    }

    /** When the Modify Appointment button is clicked, the view redirects to the Modify Appointment form
     * @param actionEvent Modify Appointment button
     * @throws IOException From FXMLLoader.
     */
    public void ToModifyAppointment(ActionEvent actionEvent) throws IOException {
        modifyingAppointment = AppointmentTable.getSelectionModel().getSelectedItem();

        if (modifyingAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No appointment selected");
            alert.setContentText("Please select a appointment");
            alert.showAndWait();
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ModifyAppointmentForm.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("MODIFY APPOINTMENT FORM");
            stage.setScene(scene);
            stage.show();
        }
    }

    /** When the Delete Appointment button is clicked, the appointment is deleted
     * @param actionEvent Delete Appointment button
     */
    public void ToDeleteAppointment(ActionEvent actionEvent) {
        modifyingAppointment = AppointmentTable.getSelectionModel().getSelectedItem();

        if (modifyingAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No appointment selected");
            alert.setContentText("Please select a appointment");
            alert.showAndWait();
        }
        else {
            int appointmentID = modifyingAppointment.getAppointmentID();
            String appointmentType = modifyingAppointment.getType();

            DBAppointments.deleteAppointment(appointmentID);
            RemoveAppointmentLabel.setText("Appointment #" + appointmentID + " [" + appointmentType + "] Was Removed");
            RemoveAppointmentLabel.setTextFill(RED);

            AppointmentTable.setItems(DBAppointments.getAllAppointments());
            AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
            Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
            Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
            Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
            Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
            End.setCellValueFactory(new PropertyValueFactory<>("End"));
            ApptCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
            UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            ContactID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        }
    }

    /** When the All Appointment radio button is clicked, All appointments displayed
     * @param actionEvent All Appointment button
     */
    public void ToAllButton(ActionEvent actionEvent) {
        AppointmentTable.setItems(DBAppointments.getAllAppointments());
        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        ApptCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        ContactID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
    }

    /** When the Month Appointment radio button is clicked, Month appointments displayed
     * @param actionEvent Month Appointment button
     */
    public void ToMonthButton(ActionEvent actionEvent) {
        AppointmentTable.setItems(DBAppointments.getMonthAppointments());
        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        ApptCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        ContactID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
    }

    /** When the Week Appointment radio button is clicked, Week appointments displayed
     * @param actionEvent Week Appointment button
     */
    public void ToWeekButton(ActionEvent actionEvent) {
        AppointmentTable.setItems(DBAppointments.getWeekAppointments());
        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        End.setCellValueFactory(new PropertyValueFactory<>("End"));
        ApptCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        UserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        ContactID.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
    }

    /** When the Reports button is clicked, the view displays the reports form
     * @param actionEvent Reports button
     */
    public void ToReportsButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ReportForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("REPORTS");
        stage.setScene(scene);
        stage.show();
    }

    /** This method checks for upcoming appointments in the next 15 minutes and displays an alert
     */
    public void checkUpcoming() {
        if (DBAppointments.checkUpcoming() != null){
            Appointment upcoming = DBAppointments.checkUpcoming();
            UpcomingAlert.setText("Next Appointment \n ID: " + upcoming.getAppointmentID() +
                    "\n Start: " + upcoming.getStart());
            UpcomingAlert.setTextFill(RED);
            UpcomingAlert.setWrapText(true);
        }
    }
}
