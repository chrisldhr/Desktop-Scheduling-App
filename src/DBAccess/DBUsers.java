package DBAccess;

import Helper.JDBC;
import Model.Contact;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBUsers {
    public static ObservableList<User> getAllUsers () {
        ObservableList<User> users = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int UserID = rs.getInt("User_ID");
                String Name = rs.getString("User_Name");
                String Password = rs.getString("Password");
                User U = new User(UserID, Name, Password);
                users.add(U);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    public static boolean checkLogin(String text, String text1) {
        try {
            String sqlcl = "SELECT * from users WHERE User_Name = ? AND Password = ?";

            PreparedStatement pscl = JDBC.getConnection().prepareStatement(sqlcl);

            pscl.setString(1, text);
            pscl.setString(2, text1);

            ResultSet rs = pscl.executeQuery();

            if (rs.next()) {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
