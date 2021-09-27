package DBAccess;

import Controller.LoginFormController;
import Helper.JDBC;
import Model.Appointment;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.*;

public class DBAppointments {
    public static ZoneId localZone = ZoneId.systemDefault();


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
                Timestamp StartUTC = rs.getTimestamp("Start");
                Timestamp EndUTC = rs.getTimestamp("End");
                int CustomerID = rs.getInt("Customer_ID");
                int UserID = rs.getInt("User_ID");
                int ContactID = rs.getInt("Contact_ID");

//                Timestamp StartLocal = convertToLocal(StartUTC);
//                Timestamp EndLocal = convertToLocal(EndUTC);

                Appointment A = new Appointment(AppointmentID, Title, Description, Location, Type, StartUTC, EndUTC, CustomerID, UserID, ContactID);
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

//            Timestamp StartUTC = convertToUTC(Start);
//            Timestamp EndUTC = convertToUTC(End);

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

//            Timestamp StartUTC = convertToUTC(startTimestamp);
//            Timestamp EndUTC = convertToUTC(endTimestamp);

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

//    public static Timestamp convertToLocal(Timestamp utc) {
//        return Timestamp.valueOf(utc.toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(localZone).toLocalDateTime());
//    }
//
//    public static Timestamp convertToUTC (Timestamp local) {
//        return Timestamp.valueOf(local.toLocalDateTime().atZone(localZone).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());
//    }

    public static Boolean checkOverlap(int customerID, Timestamp start, Timestamp end, int appointmentID) {
        try {
            String sqlco = "SELECT * from appointments WHERE Customer_ID = ? " +
                    "AND ((? >= start AND ? < end)" +
                    "OR (? > start AND ? <= end)" +
                    "OR (? <= start AND ? >= end))" +
                    "AND (Appointment_ID != ?) ";
                    // StartUTC ? BETWEEN start AND end
            PreparedStatement psco = JDBC.getConnection().prepareStatement(sqlco);

//            Timestamp StartUTC = convertToUTC(start);
//            Timestamp EndUTC = convertToUTC(end);

            psco.setInt(1, customerID);
            psco.setTimestamp(2, start);
            psco.setTimestamp(3, start);
            psco.setTimestamp(4, end);
            psco.setTimestamp(5, end);
            psco.setTimestamp(6, start);
            psco.setTimestamp(7, end);
            psco.setInt(8, appointmentID);

            ResultSet rs = psco.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public static ObservableList<Appointment> getMonthAppointments() {
        LocalTime nowTime = LocalTime.now();
        LocalDate nowDate = LocalDate.now();
        String current = String.valueOf(LocalDateTime.now().getMonth());
        LocalDate nowDatePlusAMonth = nowDate.plusMonths(1);
        Timestamp now = Timestamp.valueOf(LocalDateTime.of(nowDate, nowTime));
        Timestamp nowPlusAMonth = Timestamp.valueOf(LocalDateTime.of(nowDatePlusAMonth, nowTime));

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE MONTHNAME(start) = ?";
                    //"start >= ? AND end <= ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

//            Timestamp nowUTC = convertToUTC(now);
//            Timestamp monthUTC = convertToUTC(nowPlusAMonth);

            ps.setString(1, current);
//            ps.setTimestamp(2, nowPlusAMonth);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int AppointmentID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                Timestamp StartUTC = rs.getTimestamp("Start");
                Timestamp EndUTC = rs.getTimestamp("End");
                int CustomerID = rs.getInt("Customer_ID");
                int UserID = rs.getInt("User_ID");
                int ContactID = rs.getInt("Contact_ID");

//                Timestamp StartLocal = convertToLocal(StartUTC);
//                Timestamp EndLocal = convertToLocal(EndUTC);

                Appointment A = new Appointment(AppointmentID, Title, Description, Location, Type, StartUTC, EndUTC, CustomerID, UserID, ContactID);
                appointments.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointments;

    }

    public static ObservableList<Appointment> getWeekAppointments() {
        LocalTime nowTime = LocalTime.now();
        LocalDate nowDate = LocalDate.now();
        LocalDate nowDatePlusAWeek = nowDate.plusWeeks(1);
        Timestamp now = Timestamp.valueOf(LocalDateTime.of(nowDate, nowTime));
        Timestamp nowPlusAWeek = Timestamp.valueOf(LocalDateTime.of(nowDatePlusAWeek, nowTime));

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE start >= ? AND end <= ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

//            Timestamp nowUTC = convertToUTC(now);
//            Timestamp weekUTC = convertToUTC(nowPlusAWeek);

            ps.setTimestamp(1, now);
            ps.setTimestamp(2, nowPlusAWeek);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int AppointmentID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                Timestamp StartUTC = rs.getTimestamp("Start");
                Timestamp EndUTC = rs.getTimestamp("End");
                int CustomerID = rs.getInt("Customer_ID");
                int UserID = rs.getInt("User_ID");
                int ContactID = rs.getInt("Contact_ID");

//                Timestamp StartLocal = convertToLocal(StartUTC);
//                Timestamp EndLocal = convertToLocal(EndUTC);

                Appointment A = new Appointment(AppointmentID, Title, Description, Location, Type, StartUTC, EndUTC, CustomerID, UserID, ContactID);
                appointments.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointments;

    }

    public static boolean check15() {
        try {
            int userID = DBUsers.getCurrentUser().getUserID();
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            Timestamp nowPlus15 = Timestamp.valueOf(LocalDateTime.now().plusMinutes(15));

            String sqlco = "SELECT * from appointments WHERE User_ID = ? AND start BETWEEN ? AND ?" ;

            PreparedStatement psco = JDBC.getConnection().prepareStatement(sqlco);

            psco.setInt(1, userID);
            psco.setTimestamp(2, now);
            psco.setTimestamp(3, nowPlus15);

            ResultSet rs = psco.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static ObservableList<String> getAllTypes () {
        ObservableList<String> types = FXCollections.observableArrayList();

        try {
            String sql = "SELECT DISTINCT type from appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("Type");
                types.add(type);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return types;
    }

    public static int getTotalAppts(String month, String type) {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE MONTHNAME(Start) = ? AND type = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1, month);
            ps.setString(2, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int AppointmentID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                Timestamp StartUTC = rs.getTimestamp("Start");
                Timestamp EndUTC = rs.getTimestamp("End");
                int CustomerID = rs.getInt("Customer_ID");
                int UserID = rs.getInt("User_ID");
                int ContactID = rs.getInt("Contact_ID");

                Appointment A = new Appointment(AppointmentID, Title, Description, Location, Type, StartUTC, EndUTC, CustomerID, UserID, ContactID);
                appointments.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointments.size();
    }

    public static ObservableList getContactAppts(int contactID) {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments WHERE Contact_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, contactID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int AppointmentID = rs.getInt("Appointment_ID");
                String Title = rs.getString("Title");
                String Description = rs.getString("Description");
                String Location = rs.getString("Location");
                String Type = rs.getString("Type");
                Timestamp StartUTC = rs.getTimestamp("Start");
                Timestamp EndUTC = rs.getTimestamp("End");
                int CustomerID = rs.getInt("Customer_ID");
                int UserID = rs.getInt("User_ID");
                int ContactID = rs.getInt("Contact_ID");

                Appointment A = new Appointment(AppointmentID, Title, Description, Location, Type, StartUTC, EndUTC, CustomerID, UserID, ContactID);
                appointments.add(A);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return appointments;

    }
}
