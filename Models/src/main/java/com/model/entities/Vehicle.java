package com.model.entities;

import javafx.beans.property.IntegerProperty;

public class Vehicle {
    private int VehicleID;
    private String VehicleName;
    private Double Price;

// Konstruktør 
    public Vehicle(int VehicleID, String VehicleName, Double Price) {
        this.VehicleID = VehicleID;
        this.VehicleName = VehicleName;
        this.Price = Price;

    }
public Vehicle() {
        //TODO Auto-generated constructor stub
    }
    // Get metode
    public IntegerProperty getVehicleID () {
        return VehicleID;
    }
// Set metode
    public void setVehicleID (int VehicleID) {
        this.VehicleID = VehicleID;
    }

    public String getVehicleName () {
        return VehicleName;
    }

    public void setVehicleName (String VehicleName) {
        this.VehicleName = VehicleName;
    }

    public Double getPrice () {
        return Price;
    }

    public void setPrice (Double Price) {
        this.Price = Price;
    }
  
}
