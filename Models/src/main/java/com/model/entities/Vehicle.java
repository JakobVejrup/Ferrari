package com.model.entities;

public class Vehicle {
    private int VehicleID;
    private String VehicleName;
    private Double Price;
    private byte[] image;

// Konstruktør 
    public Vehicle(int VehicleID, String VehicleName, Double Price, byte[] image) {
        this.VehicleID = VehicleID;
        this.VehicleName = VehicleName;
        this.Price = Price;
        this.image = image;
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

    public Double getPrice () {
        return Price;
    }

    public void setPrice (Double Price) {
        this.Price = Price;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }
    public byte[] getImage() {
        return image;
    }
}
