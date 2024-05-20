package com.presentation.mvc.models.vehicle;

import java.util.ArrayList;
import java.util.List;
import com.model.entities.Vehicle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class VehicleModel extends Vehicle {
    private IntegerProperty vehicleIdProp;
    private StringProperty nameProp;
    private DoubleProperty priceProp;
    private ObjectProperty<byte[]> imageProp;

    public VehicleModel() {
        vehicleIdProp = new SimpleIntegerProperty();
        nameProp = new SimpleStringProperty();
        priceProp = new SimpleDoubleProperty();  
        imageProp = new SimpleObjectProperty<>();
    }
    
    public VehicleModel(Vehicle vehicle) {
        this();
        setVehicleID(vehicle.getVehicleID());
        nameProp.set(vehicle.getVehicleName());
        priceProp.set(vehicle.getPrice());
        imageProp.set(vehicle.getImage());
    }
    @Override
    public byte[] getImage() {
        return imageProp.get();
    }
    @Override
    public void setImage(byte[] image) {
        imageProp.set(image);
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
    public IntegerProperty vehicleIdProperty() {
        return vehicleIdProp;
    }
    public StringProperty nameProperty() {
        return nameProp;
    }
    public DoubleProperty priceProperty() {
        return priceProp;
    }

    public static List<VehicleModel> makeModels(List<Vehicle> vehicles) {
        List<VehicleModel> models = new ArrayList<>();
        for (Vehicle vehicle : vehicles)
            models.add(new VehicleModel(vehicle));
        return models;
    }
    public static List<Object> makeModelsAsObjects(List<Vehicle> vehicles) {
        List<Object> models = new ArrayList<>();
        for(Vehicle vehicle : vehicles)
            models.add(new VehicleModel(vehicle));
        return models;
    } 
}
