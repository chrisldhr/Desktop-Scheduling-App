package Model;

public class Country {
    private int CountryID;
    private String CountryName;

    public Country(int CountryID, String CountryName) {
        this.CountryID = CountryID;
        this.CountryName = CountryName;
    }

    public int getCountryID() {
        return CountryID;
    }
    public void setCountryID(int CountryID) {
        this.CountryID = CountryID;
    }

    public String getCountryName() {
        return CountryName;
    }
    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }
}
