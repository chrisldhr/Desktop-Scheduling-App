package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Country {
    private int CountryID;
    private String CountryName;
    private static ObservableList allCountries = FXCollections.observableArrayList();

    public Country(int CountryID, String CountryName) {
        this.CountryID = CountryID;
        this.CountryName = CountryName;
    }

    public static ObservableList getAllCountries() {
        return allCountries;
    }

    public int getCountryID() {
        return CountryID;
    }
    public void setCountryID(int CountryID) {
        this.CountryID = CountryID;
    }

    public String getCountryName() {
        return CountryName;
    }
    public void setCountryName(String CountryName) { this.CountryName = CountryName; }

    //For displaying object in combobox
    @Override
    public String toString(){
        return ("#" + Integer.toString(CountryID) + " " + CountryName);
    }
}
