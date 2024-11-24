package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;


//klasse der håndterer data for åbne aftaler og laver stored procedures til databasen
public class AgreementOpenData implements Data {
    private ConnectionData db;
    private InvoiceData invoiceData;
    private CustomerData customerData;
    private EmployeeData employeeData;
    public AgreementOpenData(ConnectionData db, InvoiceData invoiceData, CustomerData customerData, EmployeeData employeeData) {
        this.db = db;
        this.invoiceData = invoiceData;
        this.customerData = customerData;
        this.employeeData = employeeData;
    }
    //opretter en aftale
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspOpenAgreementInsert(?,?,?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
            cs.setInt("FixedTerms", agreement.getFixedTerms());
            cs.setDouble("StartValue", agreement.getStartValue());
            cs.setDate("StartAgreement", agreement.getStartAgreement());
            cs.setString("Rki", agreement.getRki().toString());
            cs.setInt("CustomerId", agreement.getCustomer().getId());
            cs.setInt("EmployeeId", agreement.getEmployee().getId());
            cs.setInt("VehicleId", agreement.getVehicle().getId());
            cs.setDouble("TotalRate", agreement.getTotalRate());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            agreement.setId(result.getInt("Id"));
            return agreement;
        } 
        catch (Exception e) {
            return null;
        }
    }
    //læser en aftale
    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspOpenAgreementGet(?)}")) {
            cs.setInt("Id", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Agreement(
                result.getInt("AgreementId"),
                result.getInt("FixedTerms"),
                result.getDouble("StartValue"),
                result.getDate("StartAgreement"),
                Rating.valueOf(result.getString("Rki")),
                (Customer)customerData.read(result.getInt("CustomerId")),
                (Employee)employeeData.read(result.getInt("EmployeeId")),
                new Vehicle(result.getInt("VehicleId"), result.getString("VehicleName"), result.getDouble("Price"), result.getBytes("VehicleImage")),
                result.getDouble("TotalRate")
            );  
        } 
        catch (Exception e) {
            return null;
        }
    }
    //læser alle aftaler
    @Override
    public Object readAll(Object parameter) {
        ArrayList<Agreement> agreements = new ArrayList<>();
        try (CallableStatement cs = db.makeCall("{call Trade.uspOpenAgreementGetAll()}")) {
            ResultSet result = cs.executeQuery();
            while (result.next()) 
                agreements.add(
                new Agreement(
                    result.getInt("AgreementId"),
                    result.getInt("FixedTerms"),
                    result.getDouble("StartValue"),
                    result.getDate("StartAgreement"),
                    Rating.valueOf(result.getString("Rki")),
                    (Customer)customerData.read(result.getInt("CustomerId")),
                    (Employee)employeeData.read(result.getInt("EmployeeId")),
                    new Vehicle(result.getInt("VehicleId"), result.getString("VehicleName"), result.getDouble("Price"), result.getBytes("VehicleImage")),
                    result.getDouble("TotalRate")
                ));
            return agreements;
            } 
        catch (Exception e) {
            return agreements;
        }
    }
    //opdaterer en aftale
    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspOpenAgreementUpdate(?,?,?,?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
            cs.setInt("Id", agreement.getId());
            cs.setInt("FixedTerms", agreement.getFixedTerms());
            cs.setDouble("StartValue", agreement.getStartValue());
            cs.setDate("StartAgreement", agreement.getStartAgreement());
            cs.setString("Rki", agreement.getRki().toString());
            cs.setInt("CustomerId", agreement.getCustomer().getId());
            cs.setInt("EmployeeId", agreement.getEmployee().getId());
            cs.setInt("VehicleId", agreement.getVehicle().getId());
            cs.setDouble("TotalRate", agreement.getTotalRate());
            cs.execute();
         return cs.getUpdateCount() > 0 ? agreement : null;
        } catch (Exception e) {
            return null;
        }
    }
    //sletter en aftale
    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspOpenAgreementDelete(?)}")) {
            cs.setInt("Id", ((Agreement)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
