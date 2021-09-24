package DBAccess;

import Helper.JDBC;
import Model.Contact;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
