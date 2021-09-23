package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Division {
    private int DivisionID;
    private String DivisionName;
    private int CountryID;
    private static ObservableList allDivisions = FXCollections.observableArrayList();

    public Division(int DivisionID, String DivisionName, int CountryID) {
        this.DivisionID = DivisionID;
        this.DivisionName = DivisionName;
        this.CountryID = CountryID;
    }

    public static ObservableList getAllDivisions() {
        return allDivisions;
    }

    public int getDivisionID() {
        return DivisionID;
    }
    public void setDivisionID(int DivisionID) {
        this.DivisionID = DivisionID;
    }

    public String getDivisionName() {
        return DivisionName;
    }
    public void setDivisionName(String DivisionName) {
        this.DivisionName = DivisionName;
    }

//    public int getCountryID() {
//        return CountryID;
//    }
//    public void setCountryID(int CountryID) {
//    this.CountryID = CountryID;
//    }

}
