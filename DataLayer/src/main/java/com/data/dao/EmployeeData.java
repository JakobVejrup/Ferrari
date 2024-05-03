package com.data.dao;

import com.data.SQLData;
import com.data.interfaces.Data;
import com.data.interfaces.UserExtra;
import com.model.entities.Employee;
import com.model.enums.Occupation;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//Anders
public class EmployeeData implements Data, UserExtra {
    private SQLData db;
    public EmployeeData(SQLData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspEmployeeInsert(?,?,?,?,?)}")) {
            Employee employee = (Employee) parameter;
            cs.setString("Name", employee.getName());
            cs.setString("PhoneNumber", employee.getPhoneNumber());
            cs.setString("Email", employee.getEmail());
            cs.setString("Occupation", employee.getOccupation().toString());
            cs.setString("Password", employee.getPassword());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            employee.setId(result.getInt("Id"));
            return employee;
        } catch (Exception e) {
            return null;
        }
    }

// no password will be given since thats knowledge only the user should have, it will be given in a login
    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspEmployeeGet(?)}")) {
            cs.setInt("Id", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Employee(result.getInt("Id"), 
            result.getString("Name"), 
            result.getString("PhoneNumber"), 
            result.getString("Email"),
            Occupation.valueOf(result.getString("Occupation")),
            result.getDouble("Limit")
            );
            } 
        catch (SQLException e) {
            return null;
        }
    }

// no password will be given since thats knowledge only the user should have, it will be given in a login
    @Override
    public Object readAll(Object parameter) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try (CallableStatement cs = db.makeCall("{call uspEmployeeGetAll()}")) {
            ResultSet result = cs.executeQuery();
            while (result.next()) 
                employees.add(new Employee(result.getInt("Id"), 
                result.getString("Name"), 
                result.getString("PhoneNumber"), 
                result.getString("Email"),
                Occupation.valueOf(result.getString("Occupation")),
                result.getDouble("Limit")
                ));
            return employees;
        } catch (Exception e) {
            return employees;
        } 
    }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspEmployeeUpdate(?,?,?,?,?)}")) {
            Employee employee = (Employee) parameter;
            cs.setString("Name", employee.getName());
            cs.setString("PhoneNumber", employee.getPhoneNumber());
            cs.setString("Email", employee.getEmail());
            cs.setInt("Id", employee.getId());
            cs.setString("Occupation", employee.getOccupation().toString());
            cs.execute();
            return cs.getUpdateCount() > 0 ? employee : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspEmployeeDelete(?)}")) {
            cs.setInt("Id", ((Employee)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Employee login(Employee login) {
        try (CallableStatement cs = db.makeCall("{call uspLogin(?, ?)}")) {
            Employee employee = (Employee) login;
            cs.setString("Email", login.getEmail());
            cs.setString("Password", login.getPassword());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            employee.setName(result.getString("Name"));
            employee.setId(result.getInt("Id"));
            employee.setPhoneNumber(result.getString("PhoneNumber"));
            employee.setOccupation(Occupation.valueOf(result.getString("Occupation")));
            employee.setLoanLimit(result.getDouble("Limit"));
            return employee;
            } 
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Employee updateSelf(Employee update) {
        try (CallableStatement cs = db.makeCall("{call uspUpdateSelfEmployee(?,?,?,?,?,?)}")) {
            cs.setString("Name", update.getName());
            cs.setString("PhoneNumber", update.getPhoneNumber());
            cs.setString("Email", update.getEmail());
            cs.setInt("Id", update.getId());
            cs.setString("Password", update.getPassword());
            cs.setString("Occupation", update.getOccupation().toString());
            cs.execute();
            return cs.getUpdateCount() > 0 ? update : null;
        } catch (Exception e) {
            return null;
        }
    }
}
