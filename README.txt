Title: C195 Performance Assessment Scheduling Application

Purpose: To allow for users to schedule appointments with customers

Author: Christopher Liu (001274582)

Contact information: cliu9@my.wgu.edu

Application version: 1.0

Date: Sep 22, 2021

IDE: IntelliJ Community 2020.1.3 || Java SE 11.0.11 || JavaFX-SDK-11.0.2

Directions:
- Please unzip folder and open in InteliJ
- File->Settings->Path Variables->Add PATH_T0_FX and link to FX library directory
- File->Project Structure->Libraries->Add FX library directory and mysql-connector-java-8.0.26
- Add to VM options in Main Configuration
"--module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics"
- Then run src/Main/Main.java

Description of additional report: Provides schedules based on description


//        LocalDateTime startBusiness = convertToLocal(LocalDateTime.of(date, LocalTime.of(8, 0)));

//    public static LocalDateTime convertToLocal (LocalDateTime EST) {
//        return EST.atZone(ZoneId.of("America/New_York")).withZoneSameInstant(localZone).toLocalDateTime();
//    }

    public static LocalDateTime convertToEST (LocalDateTime local) {
        return local.atZone(localZone).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
    }

//    public static Timestamp convertToLocal(Timestamp utc) {
//        return Timestamp.valueOf(utc.toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(localZone).toLocalDateTime());
//    }

    public static LocalDateTime convertToEST (LocalDateTime local) {
        return local.atZone(localZone).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
    }

    public static LocalDateTime convertToLocal (LocalDateTime est) {
        return est.atZone(ZoneId.of("America/New_York")).withZoneSameInstant(localZone).toLocalDateTime();
    }

//            ResultSet rs = psac.getGeneratedKeys();
//            rs.next();
//            Statement statement = JDBC.getConnection().createStatement();
//            String queryCustomer = "INSERT INTO customers SET Customer_Name='" + name + "', Address='" + address + "', Postal='" + postal + "', Phone='" + phone + "', Create_Date=NOW(), Script='script', Last_Update=NOW(), Last_Updated_By='script', Division_ID='" + division + "'";
//            statement.executeUpdate(queryCustomer);

public void ToStartCombo(ActionEvent actionEvent) {
        LocalTime selectedStart = StartCombo.getValue();
        LocalTime selectedEnd = LocalTime.of(22,0);

        EndCombo.getItems().clear();

        while(selectedStart.isBefore(selectedEnd)) {
            EndCombo.getItems().add(selectedStart.plusHours(1));
            selectedStart = selectedStart.plusHours(1);
        }
    }

    public void ToEndCombo(ActionEvent actionEvent) {
        LocalTime selectedStart = LocalTime.of(8,0);
        LocalTime selectedEnd = StartCombo.getValue();

        StartCombo.getItems().clear();

        while(selectedStart.isBefore(selectedEnd)) {
            StartCombo.getItems().add(selectedStart);
            selectedStart = selectedStart.plusHours(1);
        }
    }

    public static Boolean checkOverlap(int customerID, LocalDateTime startDateTime, LocalDateTime endDateTime) {
            ObservableList<Appointment> Appointments = DBAppointments.getAppointmentsByCustomer(customerID);

            for (Appointment A : Appointments) {
                if ((startDateTime.isAfter(A.getStart().toLocalDateTime()) &&
                        startDateTime.isBefore(A.getEnd().toLocalDateTime())) ||
                (endDateTime.isAfter(A.getStart().toLocalDateTime()) &&
                        endDateTime.isBefore(A.getEnd().toLocalDateTime())) ||
                        (startDateTime.isBefore(A.getStart().toLocalDateTime()) &&
                        endDateTime.isAfter(A.getEnd().toLocalDateTime()))) {
                    return true;
                }
            }
            return false;