package com.data.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.ConnectionData;
import com.data.interfaces.Data;
import com.model.entities.City;
//anders
public class CityData implements Data {

    private ConnectionData db;
    public CityData(ConnectionData db) {
        this.db = db;
    }
    @Override
    public Object create(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCityInsert(?,?)}")) {
            City city = (City) parameter;
            cs.setInt("Zip", city.getZip());
            cs.setString("Name", city.getName());
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;   
            return city;   
        } catch (Exception e) {
            return null;     
        }
    } 

    @Override
    public Object read(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCityGet(?)}")) {
            cs.setInt("Zip", (int)parameter);
            ResultSet result = cs.executeQuery();
            if (!result.next())
                return null;
            return new City((int)parameter,
            result.getString("CityName")
            );
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Object readAll(Object parameter) {
        ArrayList<City> cities = new ArrayList<>();
        try (CallableStatement cs = db.makeCall("{call Person.uspCityGetAll()}")) {
            ResultSet result = cs.executeQuery();
            while (result.next()) {
                cities.add(new City(
                    result.getInt("Zip"),
                    result.getString("CityName")
                ));
            }
            return cities;
        } catch (SQLException e) {
            return cities;
        }
    }

    @Override
    public Object update(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCityUpdate(?,?)}")) {
            City city = (City) parameter;
            cs.setInt("Zip", city.getZip());
            cs.setString("Name", city.getName());
            return cs.executeUpdate() > 0 ? city : null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean delete(Object parameter) {
        try (CallableStatement cs = db.makeCall("{call Person.uspCityDelete(?)}")) {
            cs.setInt("Zip", (int)parameter);
            cs.execute();
            return cs.getUpdateCount() > 0;
        } catch (SQLException e) {
            return false;
            
            
        }
    }
    
}
