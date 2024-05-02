package com.data.dao;

import com.data.Data;
import com.data.SQLData;
import com.model.entities.Employee;

import java.sql.CallableStatement;
import java.sql.ResultSet;

public class EmployeeData implements Data {
    private SQLData db;
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspEmployeeInsert(?,?,?,?,?)}")) {
            Employee employee = (Employee) parameter;
            cs.setString("name", employee.getName());
            cs.setString("phonenumber", employee.getPhoneNumber());
            cs.setString("email", employee.getEmail());
            cs.setString("level", employee.getRank().toString());
            cs.setString("password", employee.getPassword().toString());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            employee.setId(result.getInt("id"));
            return employee;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object read(Object parameter) {
        return null;
    }

    @Override
    public Object readAll(Object parameter) {
        return null;
    }

    @Override
    public Object update(Object parameter) {
        return null;
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspEmployeeDelete(?)}")) {
            cs.setInt("id", ((Employee)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
