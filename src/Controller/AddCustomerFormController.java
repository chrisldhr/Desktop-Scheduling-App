package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Country;
import Model.Division;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the controller class for the add customer form
 * */

public class AddCustomerFormController implements Initializable {
    public TextField NameText;
    public TextField AddressText;
    public TextField PostalText;
    public TextField PhoneText;
    public ComboBox<Country> CountryCombo;
    public ComboBox<Division> DivisionCombo;

    /** When the Add button is clicked, the customer is saved to the database and redirects to the schedule form
     * @param actionEvent Add button
     * @throws IOException If input values are invalid
     */
    public void ToAddButton(ActionEvent actionEvent) throws IOException {
        try {
            String name = NameText.getText();
            String address = AddressText.getText();
            String postal = PostalText.getText();
            String phone = PhoneText.getText();
            int division = DivisionCombo.getValue().getDivisionID();

            if (name.isEmpty() || address.isEmpty() || postal.isEmpty() || phone.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("EMPTY FIELDS");
                alert.setContentText("Please fill in all the fields");
                alert.showAndWait();
            }
            else {
                DBCustomers.addCustomer(name, address, postal, phone, division);

                Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("SCHEDULER");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("EMPTY FIELDS");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        }
    }

    /** When the cancel button is clicked, the view redirects to the schedule form
     * @param actionEvent Cancel button
     * @throws IOException From FXMLLoader.
     */
    public void ToCancelButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
    }

    /** Initializes the form with values from countries table
     * @param url The url used to resolve relative paths
     * @param resourceBundle The resourceBundle used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryCombo.setItems(DBCountries.getAllCountries());

        CountryCombo.setVisibleRowCount(5);
        DivisionCombo.setVisibleRowCount(5);

    }

    /** When a country is chosen in the combo box, the division combo box only shows available divisions
     * @param actionEvent Country combo box
     */
    public void ToCountryCombo(ActionEvent actionEvent) {
        int countryID = CountryCombo.getValue().getCountryID();
        DivisionCombo.setItems(DBDivisions.getMatchedDivisions(countryID));
    }
}
