package com.presentation.mvc.models.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.model.entities.Vehicle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VehicleModel extends Vehicle {
    private IntegerProperty vehicleIdProp;
    private StringProperty nameProp;
    private DoubleProperty priceProp;

    public VehicleModel() {
        setup();
    }

    public VehicleModel(Vehicle vehicle) {
        setup();
        setVehicleID(getVehicleID());
        vehicleIdProp.set(vehicle.getVehicleID());
        nameProp.set(vehicle.getVehicleName());
        priceProp.set(vehicle.getPrice());
    }

    @Override
    public int getVehicleID() {
        return vehicleIdProp.get();
    }
    @Override
    public String getVehicleName() {
        return nameProp.get();
    }
    @Override
    public Double getPrice() {
        return priceProp.get();
    }
    @Override
    public void setVehicleID(int id) {
        vehicleIdProp.set(id);
    }
    @Override
    public void setVehicleName(String name) {
        nameProp.set(name);
    }
    @Override
    public void setPrice(Double price) {
        priceProp.set(price);
    }
    public void setup() {
        vehicleIdProp = new SimpleIntegerProperty();
        nameProp = new SimpleStringProperty();
        priceProp = new SimpleDoubleProperty();
    }
    public IntegerProperty vehicleIntegerProperty() {
        return vehicleIdProp;
    }
    public StringProperty vehicleStringProperty() {
        return vehicleStringProperty();
    }
    public DoubleProperty priceDoubleProperty() {
        return priceDoubleProperty();
    }

    public static List<VehicleModel> makeModels(List<Vehicle> vehicle) {
        List<VehicleModel> models = new ArrayList<>();
        for (Vehicle vehicle : vehicle)
            models.add(new VehicleModel(vehicle));
        return models;
    }
}
