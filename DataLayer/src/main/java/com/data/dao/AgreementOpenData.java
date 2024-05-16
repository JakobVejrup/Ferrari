package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;

//Karl
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

    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspOpenAgreementInsert(?,?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
            cs.setInt("FixedTerms", agreement.getfixedterms());
            cs.setDouble("StartValue", agreement.getStartvalue());
            cs.setDate("StartAgreement", agreement.getStartagreement());
            cs.setString("Rki", agreement.getRKi().toString());
            cs.setInt("CustomerId", agreement.getCustomer().getId());
            cs.setInt("EmployeeId", agreement.getEmployee().getId());
            cs.setInt("VehicleId", agreement.getVehicle().getVehicleID());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            agreement.setId(result.getInt("Id"));
            for(Invoice invoice : agreement.getPayments())
                invoiceData.create(invoice);
            return agreement;
        } catch (Exception e) {
            return null;
        }
    }
    

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspOpenAgreementGet(?)}")) {
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
                new Vehicle(result.getInt("VehicleId"), result.getString("VehicleName"), result.getDouble("Price"))
            );  
        } 
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object readAll(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspOpenAgreementGetAll()}")) {
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
                new Vehicle(result.getInt("VehicleId"), result.getString("VehicleName"), result.getDouble("Price"))
            );
            } 
        catch (Exception e) {
            return null;
        }
       }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspOpenAgreementUpdate(?,?,?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
            cs.setInt("Id", agreement.getfixedterms());
            cs.setInt("FixedTerms", agreement.getfixedterms());
            cs.setDouble("StartValue", agreement.getStartvalue());
            cs.setDate("StartAgreement", agreement.getStartagreement());
            cs.setString("Rki", agreement.getRKi().toString());
            cs.setInt("CustomerId", agreement.getCustomer().getId());
            cs.setInt("EmployeeId", agreement.getEmployee().getId());
            cs.setInt("VehicleId", agreement.getVehicle().getVehicleID());
            cs.executeQuery();
         return cs.getUpdateCount() > 0 ? agreement : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspOpenAgreementDelete(?)}")) {
            cs.setInt("Id", (int)parameter);
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
}
