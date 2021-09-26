package Main;

import Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    ResourceBundle RB = ResourceBundle.getBundle("Login", Locale.getDefault());

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"));
        primaryStage.setTitle(RB.getString("login"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //Locale.setDefault(new Locale("fr"));

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
