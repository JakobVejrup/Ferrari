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
        try (CallableStatement cs = db.makeCall("{call spCustomerInsert(?,?,?,?,?,?)}")) {
            Customer customer = (Customer) parameter;
            cs.setString("Name", customer.getName());
            cs.setString("PhoneNumber", customer.getPhoneNumber());
            cs.setString("Email", customer.getEmail());
            cs.setString("Address", customer.getAddress());
            cs.setString("City_Zip", customer.getCityZip());
            cs.setString("CPR", customer.getCpr());
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
        try (CallableStatement cs = db.makeCall("{call spCustomerSelect(?)}")) {
            cs.setInt("Id", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Customer(result.getInt("Id"),
            result.getString("Name"),
            result.getString("PhoneNumber"),
            result.getString("Email"),
            result.getString("Address"),
            result.getString("City_Zip"),
            result.getString("CPR")
            );
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Object readAll(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call spCustomerSelectAll()}")) {
            ResultSet result = cs.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();
            while (result.next()) {
                customers.add(new Customer(result.getInt("Id"),
                result.getString("Name"),
                result.getString("PhoneNumber"),
                result.getString("Email"),
                result.getString("Address"),
                result.getString("City_Zip"),
                result.getString("CPR")
                ));
            }
            return customers;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call spCustomerUpdate(?,?,?,?,?,?,?,?)}")) {
            Customer customer = (Customer) parameter;
            cs.setString("Name", customer.getName());
            cs.setString("PhoneNumber", customer.getPhoneNumber());
            cs.setString("Email", customer.getEmail());
            cs.setString("Address", customer.getAddress());
            cs.setString("City_Zip", customer.getCityZip());
            cs.setString("CPR", customer.getCpr());
            cs.setInt("Id", customer.getId());
            return cs.executeUpdate() > 0 ? customer : null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call spCustomerDelete(?)}")) {
            cs.setInt("Id", ((Customer)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (SQLException e) {
            return false;
            
            
        }
    }
}
