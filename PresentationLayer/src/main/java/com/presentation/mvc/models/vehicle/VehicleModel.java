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



public class VehicleModel<NumberProperty> extends Vehicle {
    private IntegerProperty vehicleIdProp;
    private StringProperty nameProp;
    private DoubleProperty priceProp;

    public VehicleModel(int vehicleID, String VehicleName, double Price) {
        super(vehicleID, VehicleName, Price);
        this.vehicleIdProp = new SimpleIntegerProperty(vehicleID); 
        this.nameProp = new SimpleStringProperty(VehicleName);
        this.priceProp = new SimpleDoubleProperty(Price);
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
    public int getVehicleID() {
        return vehicleIdProp.get();
    }
    public void setVehicleID(int vehicleIdProp) {
        this.vehicleIdProp.set(getVehicleID());
    }
    public String getName() {
        return nameProp.get();
    }
    public void setName(String name) {
        this.nameProp.set(name);
    }
    public Double getPrice() {
        return priceProp.get();
    }
    public void setPrice(Double price) {
        this.priceProp.set(price);
    }

    public static List<VehicleModel> makeModels(List<Vehicle> vehicles) {
        List<VehicleModel> vehicleModels = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleModels.add(new VehicleModel(vehicle.getVehicleID(), vehicle.getVehicleName(), vehicle.getPrice()));
        }
        return vehicleModels;
    }
}
