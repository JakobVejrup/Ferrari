package com.model.entities;

public class Customer extends Information{
    private String address;
    private String cpr;
    private City city;
    //Magnus
    public Customer(int id, String name, String phoneNumber, String email, String address, String cpr, City city) {
        super(id, name, phoneNumber, email);
        this.address = address;
        this.cpr = cpr;
        this.city = city;
    }
    public Customer() {}
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
     
    public void copy(Customer other) {
        setEmail(other.getEmail());
        setName(other.getName());
        setPhoneNumber(other.getPhoneNumber());
        this.address = other.address;
        this.cpr = other.cpr;
        this.city = other.city;
    }



}