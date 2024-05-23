package com.model.entities;

public class Vehicle {
    private int id;
    private String name;
    private Double price;
    private byte[] image;

// Konstruktør 
    public Vehicle(int id, String name, Double price, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public Vehicle(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Vehicle() {
        image = new byte[0];
        price = 0d;
    }
    // Get metode
    public int getId () {
        return id;
    }
// Set metode
    public void setId (int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Double getPrice () {
        return price;
    }

    public void setPrice (Double price) {
        this.price = price;
    }
    
    public void setImage(byte[] image) {
        this.image = image;
    }
    public byte[] getImage() {
        return image;
    }
    public void copy(Vehicle other) {
        this.id = other.id;
        this.price = other.price;
        this.name = other.name;
        this.image = other.image;

    }
}
