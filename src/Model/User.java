package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the user class that contains methods for getters and setters
 * */

public class User {
    private int UserID;
    private String UserName;
    private String UserPassword;
    private static ObservableList allUsers = FXCollections.observableArrayList();

    /**
     * This is an empty constructor for creating user objects
     */
    public User () {}

    /**
     * This is the full constructor for creating user objects
     * @param UserID The user ID
     * @param UserName The user Name
     * @param UserPassword The user Password
     */
    public User(int UserID, String UserName, String UserPassword) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.UserPassword = UserPassword;
    }

    // Getters
    /**This method returns allUsers
     * @return allUsers
     */
    public static ObservableList getAllUsers() {
        return allUsers;
    }

    /**This method returns UserID
     * @return UserID
     */
    public int getUserID() {
        return UserID;
    }

    /**This method returns UserName
     * @return UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**This method returns UserPassword
     * @return UserPassword
     */
    public String getUserPassword() {
        return UserPassword;
    }

    // Setters
    /** This method sets the UserID
     * @param UserID the UserID to set
     */
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    /** This method sets the UserName
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) { this.UserName = UserName; }

    /** This method sets the UserPassword
     * @param UserPassword the UserPassword to set
     */
    public void setUserPassword(String UserPassword) { this.UserPassword = UserPassword; }


    /** This method formats the object to display properly in the combobox
     */
    @Override
    public String toString(){
        return ("#" + Integer.toString(UserID) + " " + UserName);
    }
}
