package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;

/**
 * This is the appointment class that contains methods for getters and setters
 * */

public class Appointment {
    private int AppointmentID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private Timestamp Start;
    private Timestamp End;
    private int CustomerID;
    private int UserID;
    private int ContactID;
    private static ObservableList allAppointments = FXCollections.observableArrayList();

    /**
     * This is the constructor for creating appointment objects
     * @param AppointmentID The appointment id
     * @param Title The appointment Title
     * @param Description The appointment Description
     * @param Location The appointment Location
     * @param Type The appointment Type
     * @param Start The appointment Start
     * @param End The appointment End
     * @param CustomerID The appointment CustomerID
     * @param UserID The appointment UserID
     * @param ContactID The appointment ContactID
     */
    public Appointment(int AppointmentID, String Title, String Description, String Location, String Type, Timestamp Start, Timestamp End, int CustomerID, int UserID, int ContactID) {
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
    /**This method returns the Appointment ID
     * @return the AppointmentID
     */
    public int getAppointmentID() { return AppointmentID; }

    /**This method returns the Appointment Title
     * @return the Title
     */
    public String getTitle() { return Title; }

    /**This method returns the Appointment Description
     * @return the Description
     */
    public String getDescription() { return Description; }

    /**This method returns the Appointment Location
     * @return the Location
     */
    public String getLocation() { return Location; }

    /**This method returns the Appointment Type
     * @return the Type
     */
    public String getType() { return Type; }

    /**This method returns the Appointment Start
     * @return the Start
     */
    public Timestamp getStart() { return Start; }

    /**This method returns the Appointment End
     * @return the End
     */
    public Timestamp getEnd() { return End; }

    /**This method returns the CustomerID
     * @return the CustomerID
     */
    public int getCustomerID() { return CustomerID; }

    /**This method returns the UserID
     * @return the UserID
     */
    public int getUserID() { return UserID; }

    /**This method returns the ContactID
     * @return the ContactID
     */
    public int getContactID() { return ContactID; }

    /**This method returns all Appointments
     * @return the allAppointments
     */
    public static ObservableList getAllAppointments() {
        return allAppointments;
    }

    // Setters
    /** This method sets the AppointmentID
     * @param AppointmentID the AppointmentID to set
     */
    public void setAppointmentID(int AppointmentID) { this.AppointmentID = AppointmentID; }

    /** This method sets the Title
     * @param Title the Title to set
     */
    public void setTitle(String Title) { this.Title = Title; }

    /** This method sets the Description
     * @param Description the Description to set
     */
    public void setDescription(String Description) { this.Description = Description; }

    /** This method sets the Location
     * @param Location the Location to set
     */
    public void setLocation(String Location) { this.Location = Location; }

    /** This method sets the Type
     * @param Type the Type to set
     */
    public void setType(String Type) { this.Type = Type; }

    /** This method sets the Start
     * @param Start the Start to set
     */
    public void setStart(Timestamp Start) { this.Start = Start; }

    /** This method sets the End
     * @param End the End to set
     */
    public void setEnd(Timestamp End) { this.End = End; }

    /** This method sets the CustomerID
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) { this.CustomerID = CustomerID; }

    /** This method sets the UserID
     * @param UserID the UserID to set
     */
    public void setUserID(int UserID) { this.UserID = UserID; }

    /** This method sets the ContactID
     * @param ContactID the ContactID to set
     */
    public void setContactID(int ContactID) { this.ContactID = ContactID; }
}
