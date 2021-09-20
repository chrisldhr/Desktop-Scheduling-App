package Controller;

import DBAccess.DBCountries;
import Model.Country;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryController implements Initializable {


    public TableView CountryTable;
    public TableColumn CountryID;
    public TableColumn CountryName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showMe(ActionEvent actionEvent) {
        ObservableList<Country> countryList = DBCountries.getAllCountries();
        for(Country C : countryList) {
            System.out.println("Country ID: " + C.getCountryID() + " Name : " + C.getCountryName());
        }

    }

}
