package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
import com.model.entities.Invoice;
import java.sql.Date;

public class InvoiceData implements Data{
    private ConnectionData db;
    public InvoiceData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspDuePaymentInsert(?,?,?,?,?,?,?,?)}")) {
            Invoice duePayment = (Invoice) parameter;
            cs.setInt("AgreementID", duePayment.getAgreement().getId());
            cs.setInt("Number", duePayment.getNumber());
            cs.setDate("DateStart", duePayment.getDatestart());
            cs.setDate("DateEnd", duePayment.getDateend());
            cs.setDouble("Plus", duePayment.getPlus());
            cs.setDouble("Minus", duePayment.getMinus());
            cs.setDouble("UltimoValue", duePayment.getUltimovalue());
            cs.setDouble("PrimoPrice", duePayment.getPrimoprice());
            cs.setString("Details", duePayment.getDetails());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return duePayment;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspDuePaymentGet(?)}")) {
            cs.setInt("Id",((Agreement)parameter).getId());
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
                result.getDouble("PrimoPrice"),
                result.getString("Details")
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object readAll(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspDuePaymentGetAll()}")) {
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
                result.getDouble("PrimoPrice"),
                result.getString("Details")
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspDuePaymentUpdate(?,?,?,?,?,?,?,?)}")) {
            Invoice duePayment = (Invoice) parameter;
            cs.setInt("AgreementID", duePayment.getAgreement().getId());
            cs.setInt("Number", duePayment.getNumber());
            cs.setDate("DateStart", duePayment.getDatestart());
            cs.setDate("DateEnd", duePayment.getDateend());
            cs.setDouble("Plus", duePayment.getPlus());
            cs.setDouble("Minus", duePayment.getMinus());
            cs.setDouble("UltimoValue", duePayment.getUltimovalue());
            cs.setDouble("PrimoPrice", duePayment.getPrimoprice());
            cs.setString("Details", duePayment.getDetails());
            cs.execute();
            return cs.getUpdateCount() > 0 ? duePayment : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspDuePaymentDelete(?)}")) {
            cs.setInt("Id", ((Agreement)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
}
