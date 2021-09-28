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
 * This is the countries database class that contains method for getting all countries
 * */

public class DBCountries {

    /**
     * This method returns all countries
     * */
    public static ObservableList<Country> getAllCountries () {
        ObservableList<Country> clist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int CountryID = rs.getInt("Country_ID");
                String CountryName = rs.getString("Country");
                Country C = new Country(CountryID, CountryName);
                clist.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return clist;
    }
}
