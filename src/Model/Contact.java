package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the contact class that contains methods for getters and setters
 * */

public class Contact {
    private int ContactID;
    private String ContactName;
    private String ContactEmail;
    private static ObservableList allContacts = FXCollections.observableArrayList();

    /**
     * This is the constructor for creating contact objects
     * @param ContactID The ContactID
     * @param ContactName The ContactName
     * @param ContactEmail The ContactEmail
     */
    public Contact(int ContactID, String ContactName, String ContactEmail) {
        this.ContactID = ContactID;
        this.ContactName = ContactName;
        this.ContactEmail = ContactEmail;
    }

    // Getters
    /**This method returns allContacts
     * @return allContacts
     */
    public static ObservableList getAllContacts() {
        return allContacts;
    }

     /**This method returns the ContactID
     * @return ContactID
     */
    public int getContactID() {
        return ContactID;
    }

    /**This method returns the ContactName
     * @return ContactName
     */
    public String getContactName() {
        return ContactName;
    }

    /**This method returns the ContactEmail
     * @return ContactEmail
     */
    public String getContactEmail() {
        return ContactEmail;
    }

    // Setters
    /** This method sets the ContactID
     * @param ContactID the ContactID to set
     */
    public void setContactID(int ContactID) {
        this.ContactID = ContactID;
    }

    /** This method sets the ContactName
     * @param ContactName the ContactName to set
     */
    public void setContactName(String ContactName) { this.ContactName = ContactName; }

    /** This method sets the ContactEmail
     * @param ContactEmail the ContactEmail to set
     */
    public void setContactEmail(String ContactEmail) { this.ContactEmail = ContactEmail; }


    /** This method formats the object to display properly in the combobox
     */
    @Override
    public String toString(){
        return ("#" + Integer.toString(ContactID) + " " + ContactName);
    }
}
