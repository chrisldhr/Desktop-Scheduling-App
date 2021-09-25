Title: C195 Performance Assessment Scheduling Application

Purpose: To allow for users to schedule appointments with customers

Author: Christopher Liu (001274582)

Contact information: cliu9@my.wgu.edu

Application version: 1.0

Date: Sep 22, 2021

IDE: IntelliJ Community 2020.1.3 || Java SE 11.0.11 || JavaFX-SDK-11.0.2

Directions: Please unzip folder and open in InteliJ. Then run src/Main/Main.java

Description of additional report: Provides schedules based on description

RUBBISH CODE

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