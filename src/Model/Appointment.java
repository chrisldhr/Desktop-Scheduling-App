package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointment {
    private int AppointmentID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private String Start;
    private String End;
    private int CustomerID;
    private int UserID;
    private int ContactID;
    private static ObservableList allAppointments = FXCollections.observableArrayList();

    public Appointment(int AppointmentID, String Title, String Description, String Location, String Type, String Start, String End, int CustomerID, int UserID, int ContactID) {
        this.AppointmentID = AppointmentID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.CustomerID = CustomerID;
        this.UserID = UserID;
        this.ContactID = ContactID;
    }

    // Getters
    public int getAppointmentID() { return AppointmentID; }
    public String getTitle() { return Title; }
    public String getDescription() { return Description; }
    public String getLocation() { return Location; }
    public String getType() { return Type; }
    public String getStart() { return Start; }
    public String getEnd() { return End; }
    public int getCustomerID() { return CustomerID; }
    public int getUserID() { return UserID; }
    public int getContactID() { return ContactID; }

    // Setters
    public void setAppointmentID(int AppointmentID) { this.AppointmentID = AppointmentID; }
    public void setTitle(String Title) { this.Title = Title; }
    public void setDescription(String Description) { this.Description = Description; }
    public void setLocation(String Location) { this.Location = Location; }
    public void setType(String Type) { this.Type = Type; }
    public void setStart(String Start) { this.Start = Start; }
    public void setEnd(String End) { this.End = End; }
    public void setCustomerID(int CustomerID) { this.CustomerID = CustomerID; }
    public void setUserID(int UserID) { this.UserID = UserID; }
    public void setContactID(int ContactID) { this.ContactID = ContactID; }


    public static ObservableList getAllAppointments() {
        return allAppointments;
    }
}
