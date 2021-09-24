package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private int UserID;
    private String UserName;
    private String UserPassword;
    private static ObservableList allUsers = FXCollections.observableArrayList();

    public User(int UserID, String UserName, String UserPassword) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.UserPassword = UserPassword;
    }

    public static ObservableList getAllUsers() {
        return allUsers;
    }

    public int getUserID() {
        return UserID;
    }
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String UserName) { this.UserName = UserName; }

    public String getUserPassword() {
        return UserPassword;
    }
    public void setUserPassword(String UserPassword) { this.UserPassword = UserPassword; }


    //For displaying object in combobox
    @Override
    public String toString(){
        return ("#" + Integer.toString(UserID) + " " + UserName);
    }
}
