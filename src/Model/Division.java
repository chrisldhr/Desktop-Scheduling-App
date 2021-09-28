package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the division class that contains methods for getters and setters
 * */

public class Division {
    private int DivisionID;
    private String DivisionName;
    private int CountryID;

    /**
     * This is the constructor for creating division objects
     * @param DivisionID The division id
     * @param DivisionName The division Name
     * @param CountryID The country ID
     */
    public Division(int DivisionID, String DivisionName, int CountryID) {
        this.DivisionID = DivisionID;
        this.DivisionName = DivisionName;
        this.CountryID = CountryID;
    }

    // Getters
    /**This method returns DivisionID
     * @return DivisionID
     */
    public int getDivisionID() {
        return DivisionID;
    }

    /**This method returns DivisionName
     * @return DivisionName
     */
    public String getDivisionName() {
        return DivisionName;
    }

    /**This method returns CountryID
     * @return CountryID
     */
    public int getCountryID() { return CountryID; }

    // Setters
    /** This method sets the DivisionID
     * @param DivisionID the DivisionID to set
     */
    public void setDivisionID(int DivisionID) { this.DivisionID = DivisionID; }

    /** This method sets the DivisionName
     * @param DivisionName the DivisionName to set
     */
    public void setDivisionName(String DivisionName) { this.DivisionName = DivisionName; }

    /** This method sets the CountryID
     * @param CountryID the CountryID to set
     */
    public void setCountryID(int CountryID) { this.CountryID = CountryID; }

    /** This method formats the object to display properly in the combobox
     */
    @Override
    public String toString(){
        return ("#" + Integer.toString(DivisionID) + " " + DivisionName);
    }
}
