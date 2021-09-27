package Controller;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBDivisions;
import Model.Country;
import Model.Customer;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyCustomerFormController implements Initializable {
    public TextField IDText;
    public TextField NameText;
    public TextField AddressText;
    public TextField PostalText;
    public TextField PhoneText;
    public ComboBox<Country> CountryCombo;
    public ComboBox<Division> DivisionCombo;
    public Customer Selected;

    public void ToModifyButton(ActionEvent actionEvent) throws IOException {
        try {
            int id = Integer.parseInt(IDText.getText());
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
            } else {
                DBCustomers.modifyCustomer(id, name, address, postal, phone, division);

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

    public void ToCancelButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ScheduleForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("SCHEDULER");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Selected = ScheduleFormController.getModifyingCustomer();
        CountryCombo.setItems(DBCountries.getAllCountries());
        DivisionCombo.setItems(DBDivisions.getAllDivisions());

        CountryCombo.setVisibleRowCount(5);
        DivisionCombo.setVisibleRowCount(5);

        IDText.setText(String.valueOf(Selected.getCustomerID()));
        NameText.setText(Selected.getName());
        AddressText.setText(String.valueOf(Selected.getAddress()));
        PostalText.setText(String.valueOf(Selected.getPostal()));
        PhoneText.setText(String.valueOf(Selected.getPhone()));

        for (int i=0; i < DivisionCombo.getItems().size(); i++) {
            Division division = DivisionCombo.getItems().get(i);
            if (division.getDivisionID() == Selected.getDivisionID()) {
                DivisionCombo.setValue(division);
                break;
            }
        }

        int countryID = DivisionCombo.getValue().getCountryID();
        for (int i=0; i < CountryCombo.getItems().size(); i++) {
            Country country = CountryCombo.getItems().get(i);
            if (country.getCountryID() == countryID) {
                CountryCombo.setValue(country);
                break;
            }
        }
//        CountryCombo.setValue(DBCountries.getMatchedCountry(countryID));
    }

    public void ToCountryCombo(ActionEvent actionEvent) {
        int countryID = CountryCombo.getValue().getCountryID();
        DivisionCombo.setItems(DBDivisions.getMatchedDivisions(countryID));
    }
}
