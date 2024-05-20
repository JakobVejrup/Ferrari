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
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.WritableDoubleValue;
import javafx.util.Callback;



public class VehicleModel extends Vehicle {
    private IntegerProperty vehicleIdProp;
    private StringProperty nameProp;
    private DoubleProperty priceProp;

    public VehicleModel() {
        setup();
    }
    
    public VehicleModel(Vehicle vehicle) {
        setup();
        setVehicleID(vehicle.getVehicleID());
        nameProp.set(vehicle.getVehicleName());
        priceProp.set(vehicle.getPrice());
    }
    @Override
    public String getVehicleID() {
        return vehicleIdProp.get();
    } 
    @Override
    public void setVehicleID(int vehicleIdProp) {
        this.vehicleIdProp.set(getVehicleID());
    }
    @Override
    public String getName() {
        return nameProp.get();
    }
    @Override
    public void setName(String name) {
        this.nameProp.set(name);
    }
    @Override
    public Double getPrice() {
        return priceProp.get();
    }
    @Override
    public void setPrice(Double price) {
        this.priceProp.set(price);
    }
    public void setup() {
        vehicleIdProp = new SimpleIntegerProperty();
        nameProp = new SimpleStringProperty();
        priceProp = new SimpleDoubleProperty();    
    }
    public IntegerProperty vehicleIdProperty() {
        return vehicleIdProp;
    }
    public StringProperty nameProperty() {
        return nameProp;
    }
    public DoubleProperty pricProperty() {
        return priceProp;
    }

    public static List<VehicleModel> makeModels(List<Vehicle> vehicles) {
        List<VehicleModel> vehicleModels = new ArrayList<>();
        for (Vehicle vehicle : vehicles) 
            models.add(new VehicleModel(vehicle));
        return vehicleModels;
    }
}
