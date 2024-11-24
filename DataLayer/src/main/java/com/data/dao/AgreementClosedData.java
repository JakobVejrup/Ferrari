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
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;

//klasse der håndterer data for lukkede aftaler
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
    //opretter en aftale
    @Override
    public Object create(Object parameter) {
        Agreement agreement = (Agreement) parameter;
        if(agreement.getId() != 0)
            delete(agreement.getId());
        try (CallableStatement cs = db.makeCall("{call Trade.uspClosedAgreementInsert(?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            cs.setInt("FixedTerms", agreement.getFixedTerms());
            cs.setDouble("StartValue", agreement.getStartValue());
            cs.setDate("StartAgreement", agreement.getStartAgreement());
            cs.setString("Rki", agreement.getRki().toString());
            cs.setInt("CustomerId", agreement.getCustomer().getId());
            cs.setInt("EmployeeId", agreement.getEmployee().getId());
            cs.setDouble("TotalRate", agreement.getTotalRate());
            cs.setDate("Start", agreement.getStart());
            cs.setDate("End", agreement.getEnd());
            cs.setString("VehicleName", agreement.getVehicle().getName());
            cs.setDouble("VehiclePrice", agreement.getVehicle().getPrice());
            cs.setDouble("EndPrice", agreement.getEndPrice());
            cs.setBytes("VehicleImage", agreement.getVehicle().getImage());
            cs.setDouble("TotalRate", agreement.getTotalRate());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            agreement.setId(result.getInt("Id"));
            agreement.getVehicle().setId(result.getInt("SaleId"));
            for(Invoice invoice : agreement.getPayments())
                invoiceData.create(invoice);
            return agreement;
        } catch (Exception e) {
            return null;
        }
    }
    //læser en aftale 
    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspClosedAgreementGet(?)}")) {
            cs.setInt("Id", ((Agreement)parameter).getId());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Agreement(
                ((Agreement)parameter).getId(),
                result.getInt("FixedTerms"),
                result.getDouble("StartValue"),
                result.getDate("StartAgreement"),
                Rating.valueOf(result.getString("Rki")),
                (Customer)customerData.read(result.getInt("CustomerId")),
                (Employee)employeeData.read(result.getInt("EmployeeId")),
                result.getDate("Start"),
                result.getDate("End"),
                new Vehicle(result.getInt("SaleId"), result.getString("SaleName"), result.getDouble("SalePrice"), result.getBytes("VehicleImage")),
                result.getDouble("EndPrice"),
                result.getDouble("TotalRate"),
                (List<Invoice>)invoiceData.read(((Agreement)parameter).getId())
            );
            } 
        catch (Exception e) {
            return null;
        }
       
    }
    //læser alle aftaler
    @Override
    public Object readAll(Object parameter) {
        List<Agreement> agreements = new ArrayList<>();
        try (CallableStatement cs = db.makeCall("{call Trade.uspClosedAgreementGetAll()}")) {
            ResultSet result = cs.executeQuery();
            while (result.next())
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
                    new Vehicle(result.getInt("SaleId"), result.getString("SaleName"), result.getDouble("SalePrice"), result.getBytes("SaleImage")),
                    result.getDouble("EndPrice"),
                    result.getDouble("TotalRate"),
                    (List<Invoice>)invoiceData.read(result.getInt("AgreementId"))
                ));
            return agreements;
        } 
        catch (Exception e) {
            return agreements;
        }
    }
    //opdaterer en aftale, men er ikke implementeret, da en lukket aftale ikke kan opdateres
    @Override
    public Object update(Object parameter) {
        return null;
    }
    //sletter en aftale
    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspClosedAgreementDelete(?)}")) {
            cs.setInt("Id", ((Agreement)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
}
