package com.model.entities;

import java.sql.Date;

public class DueDatePayment {
    private Agreement agreement;
    private int number;
    private Date datestart;
    private Date dateend;
    private double plus;
    private double minus;
    private double ultimovalue;
    private double primoprice;
    private String details;


    public DueDatePayment(Agreement agreement, int number, Date datestart, Date dateend, double plus, double minus,
            double ultimovalue, double primoprice, String details) {
        this.agreement = agreement;
        this.number = number;
        this.datestart = datestart;
        this.dateend = dateend;
        this.plus = plus;
        this.minus = minus;
        this.ultimovalue = ultimovalue;
        this.primoprice = primoprice;
        this.details = details;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public double getPlus() {
        return plus;
    }

    public void setPlus(double plus) {
        this.plus = plus;
    }

    public double getMinus() {
        return minus;
    }

    public void setMinus(double minus) {
        this.minus = minus;
    }

    public double getUltimovalue() {
        return ultimovalue;
    }

    public void setUltimovalue(double ultimovalue) {
        this.ultimovalue = ultimovalue;
    }

    public double getPrimoprice() {
        return primoprice;
    }

    public void setPrimoprice(double primoprice) {
        this.primoprice = primoprice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
