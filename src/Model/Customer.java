package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    private int CustomerID;
    private String Name;
    private String Address;
    private String Postal;
    private String Phone;
    private int DivisionID;
    private static ObservableList allCustomers = FXCollections.observableArrayList();

    public Customer(int CustomerID, String Name, String Address, String Postal, String Phone, int DivisionID) {
        this.CustomerID = CustomerID;
        this.Name = Name;
        this.Address = Address;
        this.Postal = Postal;
        this.Phone = Phone;
        this.DivisionID = DivisionID;
    }

    // Getters
    public int getCustomerID() { return CustomerID; }
    public String getName() { return Name; }
    public String getAddress() { return Address; }
    public String getPostal() { return Postal; }
    public String getPhone() { return Phone; }
    public int getDivisionID() { return DivisionID; }

    // Setters
    public void setCustomerID(int CustomerID) { this.CustomerID = CustomerID; }
    public void setName(String Name) { this.Name = Name; }
    public void setAddress(String Address) { this.Address = Address; }
    public void setPostal(String Postal) { this.Postal = Postal; }
    public void setPhone(String Phone) { this.Phone = Phone; }
    public void setDivisionID(int DivisionID) { this.DivisionID = DivisionID; }

    public static ObservableList getAllAppointments() {
        return allCustomers;
    }
}

