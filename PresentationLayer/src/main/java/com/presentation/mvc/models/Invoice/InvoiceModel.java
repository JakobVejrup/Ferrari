package com.presentation.mvc.models.Invoice;

import java.sql.Date;

import com.model.entities.Agreement;
import com.model.entities.Invoice;

import javafx.beans.property.*;

public class InvoiceModel extends Invoice{
    private IntegerProperty numberProperty;
    private ObjectProperty<Date> dateStartProperty;
    private ObjectProperty<Date> dateEndProperty;
    private DoubleProperty plusProperty;
    private DoubleProperty minusProperty;
    private DoubleProperty ultimProperty;
    private DoubleProperty primoProperty;
    private StringProperty detailsProperty;
    public InvoiceModel() {
        numberProperty = new SimpleIntegerProperty();
        dateStartProperty = new SimpleObjectProperty<>();
        dateEndProperty = new SimpleObjectProperty<>();
        plusProperty = new SimpleDoubleProperty();
        minusProperty = new SimpleDoubleProperty();
        ultimProperty = new SimpleDoubleProperty();
        primoProperty = new SimpleDoubleProperty();
        detailsProperty = new SimpleStringProperty();
    }
    public InvoiceModel(Invoice other) {
        this();
        numberProperty.set(other.getNumber());
        dateStartProperty.set(other.getDatestart());
        dateEndProperty.set(other.getDateend());
        plusProperty.set(other.getPlus());
        minusProperty.set(other.getMinus());
        ultimProperty.set(other.getUltimovalue());
        primoProperty.set(other.getPrimoprice());
        detailsProperty.set(other.getDetails());
    }
    public IntegerProperty numberProperty() {
        return numberProperty;
    }
    public ObjectProperty<Date> dateStartProperty() {
        return dateStartProperty;
    }
    public ObjectProperty<Date> dateEndProperty() {
        return dateEndProperty;
    }


    public DoubleProperty plusProperty() {
        return plusProperty;
    }

    public DoubleProperty minusProperty() {
        return minusProperty;
    }

    public DoubleProperty ultimProperty() {
        return ultimProperty;
    }

    public DoubleProperty primoProperty() {
        return primoProperty;
    }

    public StringProperty detailsProperty() {
        return detailsProperty;
    }
    @Override
    public void setNumber(int value) {
        numberProperty.set(value);
    }
    @Override
    public int getNumber() {
        return numberProperty.get();
    }
    @Override
    public Date getDatestart() {
        return dateStartProperty.get();
    }
    @Override
    public void setDatestart(Date dateStart) {
        dateStartProperty.set(dateStart);
    }
    @Override
    public Date getDateend() {
        return dateEndProperty.get();
    }
    @Override
    public void setDateend(Date dateEnd) {
        dateEndProperty.set(dateEnd);
    }
    @Override
    public double getPlus() {
        return plusProperty.get();
    }
    @Override
    public void setPlus(double plus) {
        plusProperty.set(plus);
    }
    @Override
    public double getMinus() {
        return minusProperty.get();
    }
    @Override
    public void setMinus(double minus) {
        minusProperty.set(minus);
    }
    @Override
    public double getUltimovalue() {
        return ultimProperty.get();
    }
    @Override
    public void setUltimovalue(double ultimoValue) {
        ultimProperty.set(ultimoValue);
    }
    @Override
    public double getPrimoprice() {
        return primoProperty.get();
    }
    @Override
    public void setPrimoprice(double primoPrice) {
        primoProperty.set(primoPrice);
    }
    @Override
    public String getDetails() {
        return detailsProperty.get();
    }
    @Override
    public void setDetails(String details) {
        detailsProperty.set(details);
    }
}
