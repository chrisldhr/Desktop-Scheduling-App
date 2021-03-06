package DBAccess;

import Helper.JDBC;
import Model.Country;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is the division database class that contains methods for getting divisions
 * */

public class DBDivisions {

    /**
     * This is the method to return all divisions
     * */
    public static ObservableList<Division> getAllDivisions () {
        ObservableList<Division> dlist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int DivisionID = rs.getInt("Division_ID");
                String DivisionName = rs.getString("Division");
                int CountryID = rs.getInt("Country_ID");
                Division D = new Division(DivisionID, DivisionName, CountryID);
                dlist.add(D);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dlist;
    }

    /**
     * This is the method to return all divisions by countryID
     * @param countryID the country ID
     * */
    public static ObservableList<Division> getMatchedDivisions (int countryID) {
        ObservableList<Division> matchedList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = \"" + countryID + "\"";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int DivisionID = rs.getInt("Division_ID");
                String DivisionName = rs.getString("Division");
                int CountryID = rs.getInt("Country_ID");
                Division D = new Division(DivisionID, DivisionName, CountryID);
                matchedList.add(D);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return matchedList;
    }
}
