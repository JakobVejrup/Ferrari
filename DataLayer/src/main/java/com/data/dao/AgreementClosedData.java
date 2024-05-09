package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import com.data.SQLData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
//Karl
public class AgreementClosedData implements Data{
    private SQLData db;
    public AgreementClosedData(SQLData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementInsert(?,?,?,?,?,?)}")) {
            Agreement agreement = (Agreement) parameter;
            cs.setInt("AgreementID", agreement.getId());
            cs.setDate("Start", agreement.getStart());
            cs.setDate("End", agreement.getEnd());
            cs.setString("VehicleName", agreement.getVehicle().getVehicleName());
            cs.setDouble("vechilePrice", agreement.getVehicle().getPrice());
            cs.setDouble("endprice", agreement.getEndprice());
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
