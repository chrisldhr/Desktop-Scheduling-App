package Controller;

import DBAccess.DBCountries;
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

    public void ToModifyButton(ActionEvent actionEvent) {
    }

    public void ToCancelButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/ScheduleForm.fxml"));
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
        CountryCombo.setValue(DBCountries.getMatchedCountry(countryID));
    }
}
