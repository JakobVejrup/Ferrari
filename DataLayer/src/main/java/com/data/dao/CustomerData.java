package com.data.dao;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.City;
import com.model.entities.Customer;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerData implements Data { 
    // Database connection
    private ConnectionData db;

    // Constructor
    public CustomerData(ConnectionData db) {
        this.db = db;
    }

    // Metode til at oprette en kunde
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerInsert(?,?,?,?,?,?)}")) {
            Customer customer = (Customer) parameter;
            // Indsætter kundeinformationerne i databasen
            cs.setString("Name", customer.getName());
            cs.setString("Phonenumber", customer.getPhoneNumber());
            cs.setString("Email", customer.getEmail());
            cs.setString("Address", customer.getAddress());
            cs.setInt("CityZip", customer.getCity().getZip());
            cs.setString("Cpr", customer.getCpr());
            //executer stored procedure og returnerer resultatet
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;   
            // Sætter kundens id til det id som databasen har givet kunden
            customer.setId(result.getInt("InformationId"));
            return customer;   
        } catch (Exception e) {
            System.out.println(e);
            return null;     
        }
    } 

    // Metode til at læse en kunde med et bestemt id
    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerGet(?)}")) {
            cs.setInt("Id", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            // Returnerer ny kunde med de informationer som databasen har givet
            return new Customer((int)parameter,
            result.getString("InformationName"),
            result.getString("Phonenumber"),
            result.getString("Email"),
            result.getString("Address"),
            result.getString("Cpr"),
            new City(result.getInt("Zip"), result.getString("CityName"))
            );
        } 
        catch (SQLException e) {
            return null;
        }
    }

    // Metode til at læse alle kunder
    @Override
    public Object readAll(Object parameter) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerGetAll()}")) {
            ResultSet result = cs.executeQuery();
            while (result.next()) {
                // Tilføjer kunderne til listen
                customers.add(new Customer(result.getInt("InformationId"),
                result.getString("InformationName"),
                result.getString("Phonenumber"),
                result.getString("Email"),
                result.getString("Address"),
                result.getString("Cpr"),
                new City(result.getInt("Zip"), result.getString("CityName"))
                ));
            }
            return customers;
        } catch (SQLException e) {
            return customers;
        }
    }

    // Metode til at opdatere en kunde
    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerUpdate(?,?,?,?,?,?,?)}")) {
            Customer customer = (Customer) parameter;
            // Set parameterne til stored procedure
            cs.setString("Name", customer.getName());
            cs.setString("Phonenumber", customer.getPhoneNumber());
            cs.setString("Email", customer.getEmail());
            cs.setString("Address", customer.getAddress());
            cs.setInt("CityZip", customer.getCity().getZip());
            cs.setString("Cpr", customer.getCpr());
            cs.setInt("Id", customer.getId());
            // Returnerer kunden hvis det lykkedes at opdatere
            return cs.executeUpdate() > 0 ? customer : null;
        } catch (SQLException e) {
            return null;
        }
    }

    // Metode til at slette en kunde med et bestemt id
    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerDelete(?)}")) {
            cs.setInt("Id", ((Customer)parameter).getId());
            // executer stored procedure og returnerer om det lykkedes
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (SQLException e) {
            return false;
            
            
        }
    }
}
