package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the country class that contains methods for getters and setters
 * */

public class Country {
    private int CountryID;
    private String CountryName;
    private static ObservableList allCountries = FXCollections.observableArrayList();

    /**
     * This is the constructor for creating country objects
     * @param CountryID The CountryID
     * @param CountryName The CountryName
     */
    public Country(int CountryID, String CountryName) {
        this.CountryID = CountryID;
        this.CountryName = CountryName;
    }

    // Getters
    /**This method returns allCountries
     * @return allCountries
     */
    public static ObservableList getAllCountries() {
        return allCountries;
    }

    /**This method returns the CountryID
     * @return CountryID
     */
    public int getCountryID() {
        return CountryID;
    }

    /**This method returns the CountryName
     * @return CountryName
     */
    public String getCountryName() {
        return CountryName;
    }

    // Setters
    /** This method sets the CountryID
     * @param CountryID the CountryID to set
     */
    public void setCountryID(int CountryID) {
        this.CountryID = CountryID;
    }

    /** This method sets the CountryName
     * @param CountryName the CountryName to set
     */
    public void setCountryName(String CountryName) { this.CountryName = CountryName; }

    /** This method formats the object to display properly in the combobox
     */
    @Override
    public String toString(){
        return ("#" + Integer.toString(CountryID) + " " + CountryName);
    }
}
