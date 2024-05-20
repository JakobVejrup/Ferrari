package com.model.entities;

public class Customer extends Information{
    private String address;
    private String cpr;
    private String cityZip;
    //Magnus
    public Customer(int id, String name, String phoneNumber, String email, String address, String cpr, String cityZip) {
        super(id, name, phoneNumber, email);
        this.address = address;
        this.cpr = cpr;
        this.cityZip = cityZip;
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

    public String getCityZip() {
        return cityZip;
    }

    public void setCityZip(String cityZip) {
        this.cityZip = cityZip;
    }
     
    public void copy(Customer other) {
        setEmail(other.getEmail());
        setName(other.getName());
        setPhoneNumber(other.getPhoneNumber());
        this.address = other.address;
        this.cpr = other.cpr;
        this.cityZip = other.cityZip;
    }



}