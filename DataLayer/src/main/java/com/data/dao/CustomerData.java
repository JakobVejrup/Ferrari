package com.data.dao;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Customer;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerData implements Data {
    private ConnectionData db;
    public CustomerData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerInsert(?,?,?,?,?,?)}")) {
            Customer customer = (Customer) parameter;
            cs.setString("Name", customer.getName());
            cs.setString("Phonenumber", customer.getPhoneNumber());
            cs.setString("Email", customer.getEmail());
            cs.setString("Address", customer.getAddress());
            cs.setString("CityZip", customer.getCityZip());
            cs.setString("Cpr", customer.getCpr());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;   
            customer.setId(result.getInt("Id"));
            return customer;   
        } catch (Exception e) {
            return null;     
        }
    } 

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerGet(?)}")) {
            cs.setInt("Id", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Customer((int)parameter,
            result.getString("InformationName"),
            result.getString("Phonenumber"),
            result.getString("Email"),
            result.getString("Address"),
            result.getString("CityZip"),
            result.getString("Cpr")
            );
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Object readAll(Object parameter) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerGetAll()}")) {
            ResultSet result = cs.executeQuery();
            while (result.next()) {
                customers.add(new Customer(result.getInt("InformationId"),
                result.getString("InformationName"),
                result.getString("Phonenumber"),
                result.getString("Email"),
                result.getString("Address"),
                result.getString("CityZip"),
                result.getString("Cpr")
                ));
            }
            return customers;
        } catch (SQLException e) {
            return customers;
        }
    }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerUpdate(?,?,?,?,?,?,?,?)}")) {
            Customer customer = (Customer) parameter;
            cs.setString("Name", customer.getName());
            cs.setString("Phonenumber", customer.getPhoneNumber());
            cs.setString("Email", customer.getEmail());
            cs.setString("Address", customer.getAddress());
            cs.setString("CityZip", customer.getCityZip());
            cs.setString("Cpr", customer.getCpr());
            cs.setInt("Id", customer.getId());
            return cs.executeUpdate() > 0 ? customer : null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCustomerDelete(?)}")) {
            cs.setInt("Id", ((Customer)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (SQLException e) {
            return false;
            
            
        }
    }
}
