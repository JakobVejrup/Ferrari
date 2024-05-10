package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;

//Karl
public class AgreementOpenData implements Data{
    private ConnectionData db;
    public AgreementOpenData(ConnectionData db) {
        this.db = db;
    }

    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspOpenAgreementInsert(?,?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
           
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            agreement.setId(result.getInt("Id"));
            return agreement;
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
