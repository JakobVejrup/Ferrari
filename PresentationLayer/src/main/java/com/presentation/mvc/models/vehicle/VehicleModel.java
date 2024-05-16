package com.presentation.mvc.models.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.model.entities.Vehicle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.WritableDoubleValue;



public class VehicleModel<NumberProperty> extends Vehicle {
    private NumberProperty vehicleIdProp;
    private StringProperty nameProp;
    private NumberProperty priceProp;

    public VehicleModel(int vehicleIdProp, String nameProp, Double priceProp) {
        this.vehicleIdProp = new NumberProperty(vehicleIdProp); 
        this.nameProp = new StringProperty(nameProp);
        this.priceProp = new NumberProperty(priceProp);
    }

    public NumberProperty vehicleIdProperty() {
        return vehicleIdProp;
    }
    public StringProperty nameProperty() {
        return nameProp;
    }
    public NumberProperty pricProperty() {
        return priceProp;
    }
    public NumberProperty getVehicleID() {
        return vehicleIdProp;
    }
    public void setVehicleID(int vehicleIdProp) {
        ((WritableDoubleValue) this.vehicleIdProp).set(vehicleIdProp);
    }
    public String getName() {
        return nameProp.get();
    }
    public void setName(String name) {
        this.nameProp.set(name);
    }
    public Double getPrice() {
        return ((ObservableDoubleValue) priceProp).get();
    }
    public void setPrice(Double price) {
        ((WritableDoubleValue) this.priceProp).set((double) priceProp);
    }
}
