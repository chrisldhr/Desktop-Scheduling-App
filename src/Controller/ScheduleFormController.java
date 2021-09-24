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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleFormController implements Initializable {
    private static Customer modifyingCustomer;
    public TableView <Appointment> ScheduleTable;
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

    public static Customer getModifyingCustomer() {
        return modifyingCustomer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScheduleTable.setItems(DBAppointments.getAllAppointments());
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
        modifyingCustomer = CustomerTable.getSelectionModel().getSelectedItem();

        Parent root = FXMLLoader.load(getClass().getResource("../view/ModifyCustomerForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("MODIFY CUSTOMER FORM");
        stage.setScene(scene);
        stage.show();
    }

    public void ToDeleteCustomer(ActionEvent actionEvent) {
        modifyingCustomer = CustomerTable.getSelectionModel().getSelectedItem();
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
    }

    public void ToAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AddAppointmentForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("ADD APPOINTMENT FORM");
        stage.setScene(scene);
        stage.show();
    }

    public void ToModifyAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ModifyAppointmentForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("MODIFY APPOINTMENT FORM");
        stage.setScene(scene);
        stage.show();
    }

    public void ToDeleteAppointment(ActionEvent actionEvent) {
    }
}
