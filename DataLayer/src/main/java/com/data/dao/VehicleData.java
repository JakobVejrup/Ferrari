package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Vehicle;

// Jakob
public class VehicleData implements Data {
    private static final Object Vehicle = null;
    private ConnectionData db;
    public VehicleData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create (Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspVehicleInsert(?,?,?)}")) {
            Vehicle vehicle = (Vehicle) parameter;
            cs.setInt("VehicleID", vehicle.getVehicleID());
            cs.setString("VehicleName", vehicle.getVehicleName());
            cs.setDouble("Price", vehicle.getPrice());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            vehicle.setVehicleID(vehicle.getVehicleID());
            return Vehicle;
        }   
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspVehicleGet(?, ?, ?)}")) {
            cs.setInt("ID",(int)(parameter));
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Vehicle(result.getInt("Id"),
            result.getString("VehicleName"),
            result.getDouble("Price"));
        }
        catch (SQLException e) {
            return null;
        }
    }

        @Override
        public Object readAll(Object parameter) {
            ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();
            try (CallableStatement cs = db.makeCall("{call uspVehicleGetAll()}")) {
                ResultSet result = cs.executeQuery();
                while (result.next())
                    vehicle.add (new Vehicle(result.getInt("Id"),
                    result.getString("VehicleName"),
                    result.getDouble("Price")
                    ));
                return Vehicle;
            }   catch (Exception e) {
                return Vehicle;
            }

        }

        @Override
        public Object update(Object parameter) {
            try (CallableStatement cs = db.makeCall("{call uspVehicleGetAll(?, ?, ?)}")) {
                Vehicle vehicle = (Vehicle) parameter;
                cs.setInt("Id", vehicle.getVehicleID());
                cs.setString("VehicleName", vehicle.getVehicleName());
                cs.setDouble("Price", vehicle.getPrice());
                cs.execute();
                return cs.getUpdateCount() > 0 ? Vehicle : null;
            }   catch (Exception e) {
                return null;
            }
        }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call uspVehicleGetAll(?)}")) {
            cs.setInt("Id", ((Vehicle)parameter).getVehicleID());
            cs.execute();
            return cs.getUpdateCount() > 0;
        }   
        catch (Exception e) {
            return false;
        }
    }
}

