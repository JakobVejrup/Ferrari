package com.data.dao;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
import com.model.entities.Invoice;

public class InvoiceData implements Data{
    private ConnectionData db;
    public InvoiceData(ConnectionData db) {
        this.db = db;
    }
    //opretter en faktura i databasen
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspInvoiceInsert(?,?,?,?,?,?,?,?,?,?)}")) {
            Invoice duePayment = (Invoice) parameter;
            cs.setInt("Id", duePayment.getAgreement().getId());
            cs.setInt("Number", duePayment.getNumber());
            cs.setDate("DateStart", duePayment.getDatestart());
            cs.setDate("DateEnd", duePayment.getDateend());
            cs.setDouble("Plus", duePayment.getPlus());
            cs.setDouble("Minus", duePayment.getMinus());
            cs.setDouble("UltimoValue", duePayment.getUltimovalue());
            cs.setDouble("PrimoValue", duePayment.getPrimoprice());
            cs.setDouble("Payed", duePayment.getPayed());
            cs.setString("Details", duePayment.getDetails());
            cs.execute();
            return cs.getUpdateCount() > 0 ? duePayment : null;
        } catch (Exception e) {
            return null;
        }
    }
    //læser en faktura
    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspInvoiceGetOne(?)}")) {
            cs.setInt("Id",(int)parameter);
            ResultSet result = cs.executeQuery();
            ArrayList<Invoice> invoices = new ArrayList<>();
            while(result.next())
                invoices.add(new Invoice(
                    result.getInt("Number"),
                    result.getDate("DateStart"),
                    result.getDate("DateEnd"),
                    result.getDouble("Plus"),
                    result.getDouble("Minus"),
                    result.getDouble("UltimoValue"),
                    result.getDouble("PrimoValue"),
                    result.getDouble("Payed"),
                    result.getString("Details")
                ));
            return invoices;
        } catch (Exception e) {
            return null;
        }
    }
    //læser alle fakturaer
    @Override
    public Object readAll(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspInvoiceGetAll()}")) {
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Invoice(
                (Agreement)parameter,
                result.getInt("Number"),
                result.getDate("DateStart"),
                result.getDate("DateEnd"),
                result.getDouble("Plus"),
                result.getDouble("Minus"),
                result.getDouble("UltimoValue"),
                result.getDouble("PrimoValue"),
                result.getDouble("Payed"),
                result.getString("Details")
            );
        } catch (Exception e) {
            return null;
        }
    }
    //opdaterer en faktura
    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspInvoiceUpdate(?,?,?,?,?,?,?,?)}")) {
            Invoice duePayment = (Invoice) parameter;
            cs.setInt("AgreementID", duePayment.getAgreement().getId());
            cs.setInt("Number", duePayment.getNumber());
            cs.setDate("DateStart", duePayment.getDatestart());
            cs.setDate("DateEnd", duePayment.getDateend());
            cs.setDouble("Plus", duePayment.getPlus());
            cs.setDouble("Minus", duePayment.getMinus());
            cs.setDouble("UltimoValue", duePayment.getUltimovalue());
            cs.setDouble("PrimoValue", duePayment.getPrimoprice());
            cs.setDouble("Payed", duePayment.getPayed());
            cs.setString("Details", duePayment.getDetails());
            cs.execute();
            return cs.getUpdateCount() > 0 ? duePayment : null;
        } catch (Exception e) {
            return null;
        }
    }
    //sletter en faktura
    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspInvoiceDelete(?)}")) {
            cs.setInt("Id", ((Agreement)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
}
