package com.model.entities;

import java.sql.Date;

import com.rki.rki.Rating;

public class Agreement {
private int id;
private int fixedterms;
private double startvalue;
private Date startagreement;
private Rating RKi;
private Date start;
private Date end;
private Vehicle vehicle;
private double endprice;
private Customer customer;
private Employee employee;


public Agreement(int id, int fixedterms, double startvalue, Date startagreement, Rating RKi) {
    this.id = id;
    this.fixedterms = fixedterms;
    this.startvalue = startvalue;
    this.startagreement = startagreement;
    this.RKi = RKi;
    }

    public Agreement(int id, Date start , Date end, Vehicle vehicle, double endprice, Customer customer, Employee employee) {
    this.start = start;
    this.end = end;
    this.vehicle = vehicle;
    this.endprice = endprice;
    this.customer = customer;
    this.employee = employee;
  
    }

    public Agreement(int id, Vehicle vehicle, Customer customer, Employee employee) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.employee = employee;
    }

    public Agreement(){

    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getfixedterms() {
        return fixedterms;
    }

    public void setfixedterms(int fixedterms) {
        this.fixedterms = fixedterms;
    }

    public double getStartvalue() {
        return startvalue;
    }

    public void setStartvalue(double startvalue) {
        this.startvalue = startvalue;
    }

    public Date getStartagreement() {
        return startagreement;
    }

    public void setStartagreement(Date startagreement) {
        this.startagreement = startagreement;
    }

    public Rating getRKi() {
        return RKi;
    }

    public void setRKi(Rating RKi) {
        RKi = RKi;

    }
    
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getEndprice() {
        return endprice;
    }

    public void setEndprice(double endprice) {
        this.endprice = endprice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}