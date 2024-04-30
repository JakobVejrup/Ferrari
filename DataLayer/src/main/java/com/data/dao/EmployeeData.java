package com.data.dao;

import com.data.Data;
import com.data.SQLData;
import com.model.entities.Employee;

import java.sql.CallableStatement;

public class EmployeeData implements Data {
    private SQLData db;
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call spEmployeeCreate(?,?,?,?,?)}")) {
            Employee employee = (Employee) parameter;
            cs.setInt("id", employee.getId());
            cs.setString("name", employee.getName());
            cs.setString("phonenumber", employee.getPhoneNumber());
            cs.setString("email", employee.getEmail());
            cs.setString("rank", employee.getRank().toString());

            cs.execute();
            return cs.getUpdateCount() > 0 ? employee : null;
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
        return false;
    }
}
