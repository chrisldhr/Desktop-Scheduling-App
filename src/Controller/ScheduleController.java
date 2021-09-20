package Controller;

import Model.Appointment;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {
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


}
