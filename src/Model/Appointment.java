package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Appointment {
    private int AppointmentID;
    private int CustomerID;
    private static ObservableList allAppointments = FXCollections.observableArrayList();

    public Appointment(int AppointmentID, int CustomerID) {
        this.AppointmentID = AppointmentID;
        this.CustomerID = CustomerID;
    }

    /**
     * @return the AppointmentID
     */
    public int getAppointmentID() {
        return AppointmentID;
    }

    /**
     * @param AppointmentID the AppointmentID to set
     */
    public void setAppointmentID(int AppointmentID) {
        this.AppointmentID = AppointmentID;
    }

    /**
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    /** This method returns all appointments
     * @return all appointments
     */
    public static ObservableList getAllAppointments() {
        return allAppointments;
    }
}
