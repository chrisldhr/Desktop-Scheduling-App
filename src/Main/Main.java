package Main;

import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

/** This is the main class that runs the application
 *<p>
 * JavaDoc folder located in src/Javadoc
 *<p>
 * @author Christopher Liu
 *<p>
 * Lambda expressions: In AddAppointmentFormController (bottom of code)
 *<p>
 * and ModifyAppointmentFormController (bottom of code)
 **/

public class Main extends Application {

    /** This method detects which language to use
     */
    ResourceBundle RB = ResourceBundle.getBundle("Login", Locale.getDefault());

    /** This method starts the login form
     * @param primaryStage sets the stage
     * @throws Exception when form not found
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"));
        primaryStage.setTitle(RB.getString("login"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    /** This is the main method that launches the application with test method for French
     * @param args The args passed to main method
     */
    public static void main(String[] args) {

        //Locale.setDefault(new Locale("fr"));

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
