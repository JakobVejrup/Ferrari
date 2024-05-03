package com.model.entities;

import com.model.enums.Occupation;
//Anders
public class Employee extends Information {
    private Occupation occupation;
    private String password;
    private Double loanLimit;

    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation, String password) {
        super(id, name, phoneNumber, email);
        this.occupation = occupation;
        this.password = password;
    }
    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation) {
        super(id, name, phoneNumber, email);
        this.occupation = occupation;
    }
    public Employee(int id, String name, String phoneNumber, String email, Occupation occupation, double loanLimit) {
        super(id, name, phoneNumber, email);
        this.occupation = occupation;
        this.loanLimit = loanLimit;
    }
    public Employee() {}


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
}
