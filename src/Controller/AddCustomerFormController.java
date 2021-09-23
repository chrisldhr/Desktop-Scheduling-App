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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {
    public TextField NameText;
    public TextField AddressText;
    public TextField PostalText;
    public TextField PhoneText;
    public ComboBox<Country> CountryCombo;
    public ComboBox<Division> DivisionCombo;

    public void ToAddButton(ActionEvent actionEvent) {
        String name = NameText.getText();
        String address = AddressText.getText();
        String postal = PostalText.getText();
        String phone = PhoneText.getText();
        //Country country = CountryCombo.getValue();
        int division = DivisionCombo.getValue().getDivisionID();

        //DBCustomers.addCustomer(name,address, postal, phone, division);



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
        CountryCombo.setItems(DBCountries.getAllCountries());


        CountryCombo.setVisibleRowCount(5);
        DivisionCombo.setVisibleRowCount(5);

        CountryCombo.setPromptText("Choose a country");
        DivisionCombo.setPromptText("Choose a division");
    }

    public void ToCountryCombo(ActionEvent actionEvent) {
        int countryID = CountryCombo.getValue().getCountryID();
        DivisionCombo.setItems(DBDivisions.getMatchedDivisions(countryID));

    }
}
