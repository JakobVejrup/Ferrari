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
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableDoubleValue;

public class VehicleModel extends Vehicle {
    private ObservableValue<Number> vehicleIdProp;
    private StringProperty nameProp;
    private DoubleProperty priceProp;

    public VehicleModel() {
        setup();
    }

    public VehicleModel(Vehicle vehicle) {
        setup();
        setVehicleID(getVehicleID());
        ((WritableDoubleValue) vehicleIdProp).set(vehicle.getVehicleID());
        nameProp.set(vehicle.getVehicleName());
        priceProp.set(vehicle.getPrice());
    }

    @Override
    public int getVehicleID() {
        return (int) ((ObservableDoubleValue) vehicleIdProp).get();
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
        ((WritableDoubleValue) vehicleIdProp).set(id);
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
    public ObservableValue<Integer> vehicleIntegerProperty() {
        return vehicleIntegerProperty();
    }
    public StringProperty vehicleStringProperty() {
        return vehicleStringProperty();
    }
    public ObservableValue<Double> priceDoubleProperty() {
        return priceDoubleProperty();
    }

    public static List<VehicleModel> makeModels(List<Vehicle> vehicle) {
        List<VehicleModel> models = new ArrayList<>();
        for (Vehicle vehicle2 : vehicle)
            models.add(new VehicleModel(vehicle2));
        return models;
    }

    public ObservableValue<String> nameProperty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nameProperty'");
    }
}
