package Controller;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Contact;
import Model.Division;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller class for the reports form
 * */
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
    public ComboBox<Contact> ContactCombo;
    public ComboBox<Division> DivisionIDCombo;
    public TableView AppointmentTable;
    public TableView CustomerTable;

    /** Initializes the form with values for combo boxes
     * @param url The url used to resolve relative paths
     * @param resourceBundle The resourceBundle used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        MonthCombo.setItems(months);
        TypeCombo.setItems(DBAppointments.getAllTypes());
        ContactCombo.setItems(DBContacts.getAllContacts());
        DivisionIDCombo.setItems(DBDivisions.getAllDivisions());

        MonthCombo.setVisibleRowCount(5);
        TypeCombo.setVisibleRowCount(5);
        ContactCombo.setVisibleRowCount(5);
        DivisionIDCombo.setVisibleRowCount(5);
    }

    /** When the Back button is clicked, the view redirects to the schedule form
     * @param actionEvent Back button
     * @throws IOException From FXMLLoader.
     */
    public void ToScheduleForm(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
    }

    /** When the Run button is clicked, the total appointments are calculated and returned
     * @param actionEvent Run button
     * @throws IOException From FXMLLoader.
     */
    public void ToRunButton(ActionEvent actionEvent) {
        String month = (String) MonthCombo.getValue();
        String type = (String) TypeCombo.getValue();

        String total = String.valueOf(DBAppointments.getTotalAppts(month, type));
        TotalText.setText(total);
    }

    /** When a contact is chosen in the combo box, the tableview displays contact appointments
     * @param actionEvent contact combo box
     */
    public void ToContactCombo(ActionEvent actionEvent) {
        int contactID = ContactCombo.getValue().getContactID();
        AppointmentTable.setItems(DBAppointments.getContactAppts(contactID));
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

    /** When a division ID is chosen in the combo box, the tableview displays customers with that division ID
     * @param actionEvent division ID combo box
     */
    public void ToDivisionIDCombo(ActionEvent actionEvent) {
        int divisionID = DivisionIDCombo.getValue().getDivisionID();
        CustomerTable.setItems(DBCustomers.getCustomersByDivision(divisionID));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Postal.setCellValueFactory(new PropertyValueFactory<>("Postal"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        DivisionID.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));
    }
}
