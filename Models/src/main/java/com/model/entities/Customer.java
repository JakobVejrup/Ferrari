package com.model.entities;

public class Customer extends Information{
    private String address;
    private String Cpr;
    private String City_Zip;

    public Customer(int id, String name, String phoneNumber, String email, String address, String cpr, String city_Zip) {
        super(id, name, phoneNumber, email);
        this.address = address;
        this.Cpr = cpr;
        this.City_Zip = city_Zip;
    }
    public Customer() {}
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpr() {
        return Cpr;
    }

    public void setCpr(String cpr) {
        Cpr = cpr;
    }

    public String getCity_Zip() {
        return City_Zip;
    }

    public void setCity_Zip(String city_Zip) {
        City_Zip = city_Zip;
    }



}