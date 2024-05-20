package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;
//Karl
public class AgreementClosedData implements Data{
    private ConnectionData db;
    private InvoiceData invoiceData;
    private CustomerData customerData;
    private EmployeeData employeeData;
    public AgreementClosedData(ConnectionData db, InvoiceData invoiceData, CustomerData customerData, EmployeeData employeeData) {
        this.db = db;
        this.invoiceData = invoiceData;
        this.customerData = customerData;
        this.employeeData = employeeData;
    }
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementInsert(?,?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
            cs.setInt("Id", agreement.getId());
            cs.setDate("Start", agreement.getStart());
            cs.setDate("End", agreement.getEnd());
            cs.setString("VehicleName", agreement.getVehicle().getVehicleName());
            cs.setDouble("VehiclePrice", agreement.getVehicle().getPrice());
            cs.setDouble("EndPrice", agreement.getEndPrice());
            cs.setBytes("VehicleImage", new byte[0]);

            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
                return agreement;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementGet(?)}")) {
            cs.setInt("Id", ((Agreement)parameter).getId());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Agreement(
                (int)parameter,
                result.getInt("FixedTerms"),
                result.getDouble("StartValue"),
                result.getDate("StartAgreement"),
                Rating.valueOf(result.getString("Rki")),
                (Customer)customerData.read(result.getInt("CustomerId")),
                (Employee)employeeData.read(result.getInt("EmployeeId")),
                result.getDate("Start"),
                result.getDate("End"),
                new Vehicle(result.getInt("SaleId"), result.getString("SaleName"), result.getDouble("SalePrice")),
                result.getDouble("EndPrice"), null
            );
            } 
        catch (Exception e) {
            return null;
        }
       
    }

    @Override
    public Object readAll(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementGetAll()}")) {
            ResultSet result = cs.executeQuery();
            List<Agreement> agreements = new ArrayList<>();
            while (result.next()) {
                agreements.add(new Agreement(
                    result.getInt("AgreementId"), 
                    result.getInt("FixedTerms"),
                    result.getDouble("StartValue"),
                    result.getDate("StartAgreement"),
                    Rating.valueOf(result.getString("Rki")),
                    (Customer)customerData.read(result.getInt("CustomerId")),
                    (Employee)employeeData.read(result.getInt("EmployeeId")),
                    result.getDate("Start"),
                    result.getDate("End"),
                    new Vehicle(result.getInt("SaleId"), result.getString("SaleName"), result.getDouble("SalePrice")),
                    result.getDouble("EndPrice")
                ));
            }
            return agreements;
        } 
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object update(Object parameter) {
        return null;
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementDelete(?)}")) {
            cs.setInt("Id", (int)parameter);
            return cs.executeUpdate() == 0;
        } catch (Exception e) {
            return false;
        }
    }
    
}
