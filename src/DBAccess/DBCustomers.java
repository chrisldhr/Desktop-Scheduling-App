package DBAccess;

import Helper.JDBC;
import Model.Appointment;
import Model.Country;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the customer database class that contains methods for getting, adding, modifying customers
 * */

public class DBCustomers {

    /**
     * This is the method to return all customers
     * */
    public static ObservableList<Customer> getAllCustomers () {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int CustomerID = rs.getInt("Customer_ID");
                String Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                int DivisionID = rs.getInt("Division_ID");
                Customer C = new Customer(CustomerID, Name, Address, Postal, Phone, DivisionID);
                customers.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customers;
    }

    /**
     * This is the method to add a customer
     * @param name the customer name
     * @param address the customer address
     * @param postal the customer postal code
     * @param phone the customer phone
     * @param division the division ID
     * */
    public static void addCustomer(String name,String address, String postal, String phone, int division) {
        try {
            String sqlac = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, CURRENT_TIMESTAMP, 'script', CURRENT_TIMESTAMP, 'script', ?)";
            PreparedStatement psac = JDBC.getConnection().prepareStatement(sqlac);
            psac.setString(1, name);
            psac.setString(2, address);
            psac.setString(3, postal);
            psac.setString(4, phone);
            psac.setInt(5, division);

            psac.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to modify a customer
     * @param id the customer id
     * @param name the customer name
     * @param address the customer address
     * @param postal the customer postal code
     * @param phone the customer phone
     * @param division the division ID
     * */
    public static void modifyCustomer(int id, String name, String address, String postal, String phone, int division) {
        try {
            String sqlmc = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Create_Date = CURRENT_TIMESTAMP, Created_By = 'script', Last_Update = CURRENT_TIMESTAMP, Last_Updated_By = 'script', Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement psmc = JDBC.getConnection().prepareStatement(sqlmc);
            psmc.setString(1, name);
            psmc.setString(2, address);
            psmc.setString(3, postal);
            psmc.setString(4, phone);
            psmc.setInt(5, division);
            psmc.setInt(6, id);

            psmc.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to delete a customer
     * @param customerID the customer ID
     * */
    public static void deleteCustomer(int customerID) {
        try {
            String sqlda = "DELETE FROM appointments WHERE Customer_ID = ?";
            PreparedStatement psda = JDBC.getConnection().prepareStatement(sqlda);
            psda.setInt(1, customerID);

            psda.execute();

            String sqldc = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement psdc = JDBC.getConnection().prepareStatement(sqldc);
            psdc.setInt(1, customerID);

            psdc.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to get a customer by division
     * @param divisionID the division ID
     * */
    public static ObservableList getCustomersByDivision(int divisionID) {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers WHERE Division_ID = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, divisionID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int CustomerID = rs.getInt("Customer_ID");
                String Name = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String Postal = rs.getString("Postal_Code");
                String Phone = rs.getString("Phone");
                int DivisionID = rs.getInt("Division_ID");
                Customer C = new Customer(CustomerID, Name, Address, Postal, Phone, DivisionID);
                customers.add(C);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return customers;
    }
}
