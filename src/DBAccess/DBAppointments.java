package DBAccess;

import Helper.JDBC;
import Model.Appointment;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DBAppointments {
    public static ObservableList<Appointment> getAllAppointments () {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int AppointmentID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                Timestamp Start = rs.getTimestamp("Start");
                Timestamp End = rs.getTimestamp("End");
                int CustomerID = rs.getInt("Customer_ID");
                int UserID = rs.getInt("User_ID");
                int ContactID = rs.getInt("Contact_ID");
                Appointment A = new Appointment(AppointmentID, Title, Description, Location, Type, Start, End, CustomerID, UserID, ContactID);
                appointments.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointments;
    }

    public static void addAppointment(String Title, String Description, String Location, String Type, Timestamp Start, Timestamp End, int CustomerID, int UserID, int ContactID) {
        try {
            String sqlaa = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, 'script', CURRENT_TIMESTAMP, 'script', ?, ?, ?)";
            PreparedStatement psaa = JDBC.getConnection().prepareStatement(sqlaa);
            psaa.setString(1, Title);
            psaa.setString(2, Description);
            psaa.setString(3, Location);
            psaa.setString(4, Type);
            psaa.setTimestamp(5, Start);
            psaa.setTimestamp(6, End);
            psaa.setInt(7, CustomerID);
            psaa.setInt(8, UserID);
            psaa.setInt(9, ContactID);

            psaa.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void modifyAppointment(int appointmentID, String title, String description, String location, String type, Timestamp startTimestamp, Timestamp endTimestamp, int customerID, int userID, int contactID) {
        try {
            String sqlma = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = CURRENT_TIMESTAMP, Created_By = 'script', Last_Update = CURRENT_TIMESTAMP, Last_Updated_By = 'script', Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
            PreparedStatement psma = JDBC.getConnection().prepareStatement(sqlma);
            psma.setString(1, title);
            psma.setString(2, description);
            psma.setString(3, location);
            psma.setString(4, type);
            psma.setTimestamp(5, startTimestamp);
            psma.setTimestamp(6, endTimestamp);
            psma.setInt(7, customerID);
            psma.setInt(8, userID);
            psma.setInt(9, contactID);
            psma.setInt(10, appointmentID);

            psma.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAppointment(int appointmentID) {
        try {
        String sqlda = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement psda = JDBC.getConnection().prepareStatement(sqlda);
        psda.setInt(1, appointmentID);

        psda.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
