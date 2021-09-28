package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the customer class that contains methods for getters and setters
 * */

public class Customer {
    private int CustomerID;
    private String Name;
    private String Address;
    private String Postal;
    private String Phone;
    private int DivisionID;
    private static ObservableList allCustomers = FXCollections.observableArrayList();

    /**
     * This is the constructor for creating customer objects
     * @param CustomerID The customer id
     * @param Name The customer Name
     * @param Address The customer Address
     * @param Postal The customer Postal
     * @param Phone The customer Phone
     * @param DivisionID The customer DivisionID
     */
    public Customer(int CustomerID, String Name, String Address, String Postal, String Phone, int DivisionID) {
        this.CustomerID = CustomerID;
        this.Name = Name;
        this.Address = Address;
        this.Postal = Postal;
        this.Phone = Phone;
        this.DivisionID = DivisionID;
    }

    // Getters
    /**This method returns allCustomers
     * @return allCustomers
     */
    public static ObservableList getAllCustomers() {
        return allCustomers;
    }

    /**This method returns CustomerID
     * @return CustomerID
     */
    public int getCustomerID() { return CustomerID; }

    /**This method returns Name
     * @return Name
     */
    public String getName() { return Name; }

    /**This method returns Address
     * @return Address
     */
    public String getAddress() { return Address; }

    /**This method returns Postal
     * @return Postal
     */
    public String getPostal() { return Postal; }

    /**This method returns Phone
     * @return Phone
     */
    public String getPhone() { return Phone; }

    /**This method returns DivisionID
     * @return DivisionID
     */
    public int getDivisionID() { return DivisionID; }

    // Setters
    /** This method sets the CustomerID
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) { this.CustomerID = CustomerID; }

    /** This method sets the Name
     * @param Name the Name to set
     */
    public void setName(String Name) { this.Name = Name; }

    /** This method sets the Address
     * @param Address the Address to set
     */
    public void setAddress(String Address) { this.Address = Address; }

    /** This method sets the Postal
     * @param Postal the Postal to set
     */
    public void setPostal(String Postal) { this.Postal = Postal; }

    /** This method sets the Phone
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) { this.Phone = Phone; }

    /** This method sets the DivisionID
     * @param DivisionID the DivisionID to set
     */
    public void setDivisionID(int DivisionID) { this.DivisionID = DivisionID; }


    /** This method formats the object to display properly in the combobox
     */
    @Override
    public String toString(){
        return ("#" + Integer.toString(CustomerID) + " " + Name);
    }
}

