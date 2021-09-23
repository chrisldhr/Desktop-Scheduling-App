package DBAccess;

import Helper.JDBC;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountries {
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
