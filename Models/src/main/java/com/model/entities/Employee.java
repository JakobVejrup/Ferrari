package com.model.entities;

import com.model.enums.Occupation;

import java.util.List;

//Anders
public class Employee extends Information {
    private Occupation occupation;
    private String password;
    private Double loanLimit;
    private byte[] image;

    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation, String password) {
        super(id, name, phoneNumber, email);
        this.occupation = occupation;
        this.password = password;
    }
    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation) {
        super(id, name, phoneNumber, email);
        this.occupation = occupation;
    }

    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation, double loanLimit, byte[] image) {
        super(id, name, phoneNumber, email);
        this.occupation = occupation;
        this.loanLimit = loanLimit;
        this.image = image;
    }
    public Employee() {}

    public void setImage(byte[] image) {
        this.image = image;
    }
    public byte[] getImage() {
        return image;
    }
    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
    public Occupation getOccupation() {
        return occupation;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public double getLoanLimit() {
        return loanLimit;
    }
    
    public void setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
    }
    public void copy(Employee other) {
        this.loanLimit = other.loanLimit;
        this.occupation = other.occupation;
        this.password = other.password;
        this.image = other.image;
        setEmail(other.getEmail());
        setName(other.getName());
        setPhoneNumber(other.getPhoneNumber());
    }
}
