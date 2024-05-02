package com.model.entities;

public class Vehicle {
    private int VehicleID;
    private String VehicleName;
    private float Price;

// Konstruktør 
    public Vehicle(int VehicleID, String VehicleName, float Price) {
        this.VehicleID = VehicleID;
        this.VehicleName = VehicleName;
        this.Price = Price;

    }
// Get metode
    public int getVehicleID () {
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

    public float getPrice () {
        return Price;
    }

    public void setPrice (float Price) {
        this.Price = Price;
    }
}
