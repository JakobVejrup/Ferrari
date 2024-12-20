package com.data.dao;

import com.data.ConnectionData;
import com.data.interfaces.CheckData;
import com.data.interfaces.Data;
import com.data.interfaces.UserExtra;
import com.model.entities.Employee;
import com.model.enums.Occupation;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeData implements Data, UserExtra, CheckData {
    private ConnectionData db;
    public EmployeeData(ConnectionData db) {
        this.db = db;
    }

    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspEmployeeInsert(?,?,?,?,?,?,?)}")) {
            Employee employee = (Employee) parameter;
            cs.setString("Name", employee.getName());
            cs.setString("PhoneNumber", employee.getPhoneNumber());
            cs.setString("Email", employee.getEmail());
            cs.setString("Occupation", employee.getOccupation().realString());
            cs.setString("Password", employee.getPassword());
            cs.setDouble("Limit", employee.getLoanLimit());
            cs.setBytes("Image", employee.getImage());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            employee.setId(result.getInt("InformationId"));
            return employee;
        } catch (Exception e) {
            return null;
        }
    }

// no password will be given since thats knowledge only the user should have, it will be given in a login
    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspEmployeeGet(?)}")) {
            cs.setInt("Id", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Employee(result.getInt("InformationId"), 
            result.getString("InformationName"), 
            result.getString("PhoneNumber"), 
            result.getString("Email"),
            Occupation.valueOf(result.getString("Occupation")),
            result.getDouble("Limit"),
            result.getBytes("EmployeeImage")
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
        try (CallableStatement cs = db.makeCall("{call Person.uspEmployeeGetAll()}")) {
            ResultSet result = cs.executeQuery();
            while (result.next()) 
                employees.add(new Employee(result.getInt("InformationId"), 
                result.getString("InformationName"), 
                result.getString("PhoneNumber"), 
                result.getString("Email"),
                Occupation.valueOf(result.getString("Occupation")),
                result.getDouble("Limit"),
                result.getBytes("EmployeeImage")
            ));
            return employees;
        } 
        catch (Exception e) {
            return employees;
        } 
    }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspEmployeeUpdate(?,?,?,?,?,?,?)}")) {
            Employee employee = (Employee) parameter;
            cs.setString("Name", employee.getName());
            cs.setString("PhoneNumber", employee.getPhoneNumber());
            cs.setString("Email", employee.getEmail());
            cs.setInt("Id", employee.getId());
            cs.setString("Occupation", employee.getOccupation().realString());
            cs.setDouble("Limit", employee.getLoanLimit());
            cs.setBytes("Image", employee.getImage());
            cs.execute();
            return cs.getUpdateCount() > 0 ? employee : null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspEmployeeDelete(?)}")) {
            cs.setInt("Id", ((Employee)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Employee login(Employee login) {
        try (CallableStatement cs = db.makeCall("{call Person.uspLogin(?, ?)}")) {
            Employee employee = (Employee) login;
            cs.setString("Email", login.getEmail());
            cs.setString("Password", login.getPassword());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            employee.setName(result.getString("InformationName"));
            employee.setId(result.getInt("InformationId"));
            employee.setPhoneNumber(result.getString("PhoneNumber"));
            employee.setOccupation(Occupation.valueOf(result.getString("Occupation")));
            employee.setLoanLimit(result.getDouble("Limit"));
            employee.setImage(result.getBytes("EmployeeImage"));
            return employee;
            } 
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Employee updateSelf(Employee update) {
        try (CallableStatement cs = db.makeCall("{call Person.uspUpdateSelfEmployee(?,?,?,?,?,?,?)}")) {
            cs.setString("Name", update.getName());
            cs.setString("PhoneNumber", update.getPhoneNumber());
            cs.setString("Email", update.getEmail());
            cs.setInt("Id", update.getId());
            cs.setString("Password", update.getPassword());
            cs.setString("Occupation", update.getOccupation().realString());
            cs.setBytes("Image", update.getImage());
            cs.execute();
            return cs.getUpdateCount() > 0 ? update : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean check(Object check) {
        try (CallableStatement cs = db.makeCall("{call Person.uspEmployeeCheckEmail(?)}")) {
            Employee employee = (Employee) check;
            cs.setString("Email", employee.getEmail());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return false;
            return result.getBoolean(1);
            } 
        catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean checkUpdate(Object check) {
        try (CallableStatement cs = db.makeCall("{call Person.uspEmployeeCheckEmailUpdate(?,?)}")) {
            Employee employee = (Employee) check;
            cs.setInt("Id", employee.getId());
            cs.setString("Email", employee.getEmail());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return false;
            return result.getBoolean(1);
            } 
        catch (SQLException e) {
            return false;
        }
    }
}
