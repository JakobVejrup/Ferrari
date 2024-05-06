package com.data.dao;

import java.sql.ResultSet;

import com.data.SQLData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
//Karl
public class AgreementData implements Data{
    private SQLData db;
    public AgreementData(SQLData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        try (CallebleStatement cs = db.makeCall("{call uspclosedAgreementInsert(?,?,?,?,?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
            cs.setInt("Start", agreement.getStart());
            cs.setInt("End", agreement.getEnd());
            cs.setdouble("endprice", agreement.getEnd());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
           
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object read(Object parameter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public Object readAll(Object parameter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readAll'");
    }

    @Override
    public Object update(Object parameter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Object parameter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
