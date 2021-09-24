package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contact {
    private int ContactID;
    private String ContactName;
    private String ContactEmail;
    private static ObservableList allContacts = FXCollections.observableArrayList();

    public Contact(int ContactID, String ContactName, String ContactEmail) {
        this.ContactID = ContactID;
        this.ContactName = ContactName;
        this.ContactEmail = ContactEmail;
    }

    public static ObservableList getAllContacts() {
        return allContacts;
    }

    public int getContactID() {
        return ContactID;
    }
    public void setContactID(int ContactID) {
        this.ContactID = ContactID;
    }

    public String getContactName() {
        return ContactName;
    }
    public void setContactName(String ContactName) { this.ContactName = ContactName; }

    public String getContactEmail() {
        return ContactEmail;
    }
    public void setContactEmail(String ContactEmail) { this.ContactEmail = ContactEmail; }


    //For displaying object in combobox
    @Override
    public String toString(){
        return ("#" + Integer.toString(ContactID) + " " + ContactName);
    }
}
