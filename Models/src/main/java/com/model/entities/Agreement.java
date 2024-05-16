package com.model.entities;

import java.sql.Date;
import java.util.List;

import com.rki.rki.Rating;

public class Agreement {
    private int id;
    private int fixedTerms;
    private double startValue;
    private Date startAgreement;
    private Rating Rki;
    private Customer customer;
    private Employee employee;
    private Date start;
    private Date end;
    private Vehicle vehicle;
    private double endPrice;
    private List<Invoice> payments;

    public Agreement(int Id, int FixedTerms, double startValue, Date startAgreement, Rating RKi, Customer customer,
            Employee employee, Date start, Date end, Vehicle vehicle, double endPrice , List<Invoice> payments) {
        this.id = Id;
        this.fixedTerms = FixedTerms;
        this.startValue = startValue;
        this.startAgreement = startAgreement;
        this.Rki = RKi;
        this.customer = customer;
        this.employee = employee;
        this.start = start;
        this.end = end;
        this.vehicle = vehicle;
        this.endPrice = endPrice;
        this.payments = payments;
    }

    public Agreement(int id, int fixedTerms, double startValue, Date startAgreement, Rating RKi, Customer customer,
            Employee employee, Vehicle vehicle) {
        this.id = id;
        this.fixedTerms = fixedTerms;
        this.startValue = startValue;
        this.startAgreement = startAgreement;
        this.Rki = RKi;
        this.customer = customer;
        this.employee = employee;
        this.vehicle = vehicle;
    }

    public Agreement(int id, Date start, Date end, Vehicle vehicle, double endPrice) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.vehicle = vehicle;
        this.endPrice = endPrice;
    }

    public Agreement() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getfixedTerms() {
        return fixedTerms;
    }

    public void setfixedTerms(int fixedterms) {
        this.fixedTerms = fixedterms;
    }

    public double getstartvalue() {
        return startValue;
    }

    public void setStartValue(double startvalue) {
        this.startValue = startvalue;
    }

    public Date getstartAgreement() {
        return startAgreement;
    }

    public void setstartAgreement(Date startagreement) {
        this.startAgreement = startagreement;
    }

    public Rating getRki() {
        return Rki;
    }

    public void setRki(Rating Rki) {
        Rki = Rki;

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

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endprice) {
        this.endPrice = endprice;
    }
    public void setPayments(List<Invoice> payments) {
        this.payments = payments;
    }
    public List<Invoice> getPayments() {
        return payments;
    }
}