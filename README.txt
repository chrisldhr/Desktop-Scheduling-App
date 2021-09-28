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

Description of additional report: Provides customers based on division ID

Lambda expressions: In AddAppointmentFormController (bottom of code)
and ModifyAppointmentFormController (bottom of code)

