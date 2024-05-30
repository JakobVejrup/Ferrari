package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.Vehicle;

// Jakob DataAccessObeject
public class VehicleData implements Data {
// Opstiller reference til ConnectionData
    private ConnectionData db;
// Laver en db parameter 
    public VehicleData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create (Object parameter) {
// Opret CallableStatement med db.makeCall
        try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleInsert(?,?,?)}")) {
// Parameteren castes til vehicle objekt
            Vehicle vehicle = (Vehicle) parameter;
// De parametre vi har brug for ligger her
            cs.setString("Name", vehicle.getName());
            cs.setDouble("Price", vehicle.getPrice());
            cs.setBytes("Image", vehicle.getImage());
            ResultSet result = cs.executeQuery();
// Udfører SQL kald til at hente resultaterne
            if (!result.next())
                return null;
            vehicle.setId(result.getInt("VehicleId"));
// Hvis resultatet får en vehicle id retuneres dette også
            return vehicle;
        }   
        catch (Exception e) {
// Skulle der opstå undtagelser retunere den null
            return null;
        }
    }

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleGet(?)}")) {
// Her bliver Id sat som parameter
            cs.setInt("Id",(int)(parameter));
// Udfører SQL kald ligesom ovenstående, bare kun med 1 parameter
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
// Her får vi resultatet på vores vehicle objekt
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
// Her vil vi returnere alle vehicles og sortere dem i en arraylist
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
// Her laver en update vehicle hvor vi caster til vehicle objektet
            try (CallableStatement cs = db.makeCall("{call Trade.uspVehicleUpdate(?, ?, ?, ?)}")) {
                Vehicle vehicle = (Vehicle) parameter;
                cs.setInt("Id", vehicle.getId());
                cs.setString("Name", vehicle.getName());
                cs.setDouble("Price", vehicle.getPrice());
                cs.setBytes("Image", vehicle.getImage());
                cs.execute();
// Retunerer vehicle som er blevet opdateret hvis getUpdateCount er større end 0
                return cs.getUpdateCount() > 0 ? vehicle : null;
            }   catch (Exception e) {
// Ellers returner den null
                return null;
            }
        }

    @Override
    public boolean delete(Object parameter) {
// Sletter en vehicle ved at få Id'et på vehicle, det sker hvis getUpdateCount er større end 0
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

