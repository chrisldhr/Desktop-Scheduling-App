package DBAccess;

import Helper.JDBC;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the contacts database class that contains method for getting all contacts
 * */

public class DBContacts {

    /**
     * This method returns all contacts
     * */
    public static ObservableList<Contact> getAllContacts () {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ContactID = rs.getInt("Contact_ID");
                String Name = rs.getString("Contact_Name");
                String Email = rs.getString("Email");
                Contact C = new Contact(ContactID, Name, Email);
                contacts.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }
}
