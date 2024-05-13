package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Agreement;
//Karl
public class AgreementClosedData implements Data{
    private ConnectionData db;
    public AgreementClosedData(ConnectionData db) {
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
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementGet(?)}")) {
            cs.setInt("Id", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Agreement(result.getInt("Id"),
            result.getDate("Start"),
            result.getDate("End"),
            result.getString("VehicleName"),
            result.getDouble("vechilePrice"),
            result.getDouble("endprice")
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
            if (!result.next())
                return null;
            return new Agreement(result.getInt("Id"), 
            result.getDate("Start"), 
            result.getDate("End"), 
            result.getString("VehicleName"),
            result.getDouble("vechilePrice"),
            result.getDouble("endprice")
            );
            } 
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementUpdate(?,?,?,?,?,?)}")) {
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
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspClosedAgreementDelete(?)}")) {
            cs.setInt("Id", (int)parameter);
            return cs.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        }
    }
    
}
