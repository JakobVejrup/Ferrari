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
    private ConnectionData db;
    public VehicleData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create (Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleInsert(?,?,?)}")) {
            Vehicle vehicle = (Vehicle) parameter;
            cs.setString("Name", vehicle.getName());
            cs.setDouble("Price", vehicle.getPrice());
            cs.setBytes("Image", vehicle.getImage());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            vehicle.setId(result.getInt("VehicleId"));
            return vehicle;
        }   
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleGet(?)}")) {
            cs.setInt("Id",(int)(parameter));
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new Vehicle(result.getInt("Id"),
            result.getString("VehicleName"),
            result.getDouble("Price"),
            result.getBytes("VehicleImage"));
        }
        catch (SQLException e) {
            return null;
        }
    }

        @Override
        public Object readAll(Object parameter) {
            ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
            try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleGetAll()}")) {
                ResultSet result = cs.executeQuery();
                while (result.next())
                    vehicles.add (new Vehicle(result.getInt("VehicleId"),
                    result.getString("VehicleName"),
                    result.getDouble("Price"),
                    result.getBytes("VehicleImage")
                    ));
                return vehicles;
            }   catch (Exception e) {
                return vehicles;
            }

        }

        @Override
        public Object update(Object parameter) {
            try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleUpdate(?, ?, ?, ?)}")) {
                Vehicle vehicle = (Vehicle) parameter;
                cs.setInt("Id", vehicle.getId());
                cs.setString("Name", vehicle.getName());
                cs.setDouble("Price", vehicle.getPrice());
                cs.setBytes("Image", vehicle.getImage());
                cs.execute();
                return cs.getUpdateCount() > 0 ? vehicle : null;
            }   catch (Exception e) {
                return null;
            }
        }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleGetAll(?)}")) {
            cs.setInt("Id", ((Vehicle)parameter).getId());
            cs.execute();
            return cs.getUpdateCount() > 0;
        }   
        catch (Exception e) {
            return false;
        }
    }
}

