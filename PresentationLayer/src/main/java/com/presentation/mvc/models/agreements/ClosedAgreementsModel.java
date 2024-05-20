package com.presentation.mvc.models.agreements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.rki.rki.Rating;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ClosedAgreementsModel extends Agreement{
    private IntegerProperty fixedTermsProp;
    private DoubleProperty startValueProp;
    private ObjectProperty<Date> startAgreementProp;
    private ObjectProperty<Rating> RKiProp;
    private ObjectProperty<Customer> customerProp;
    private ObjectProperty<Employee> employeeProp;
    private ObjectProperty<Vehicle> vehicleProp;
    private ObjectProperty<Date> startProp;
    private ObjectProperty<Date> endProp;
    private DoubleProperty endpriceProp;

    public ClosedAgreementsModel() {
        setup();
    }

    public ClosedAgreementsModel(Agreement agreement) {
        setup();
        setId(agreement.getId());
        fixedTermsProp.set(agreement.getfixedTerms());
        startValueProp.set(agreement.getstartvalue());
        startAgreementProp.set(agreement.getstartAgreement());
        RKiProp.set(agreement.getRki());
        customerProp.set(agreement.getCustomer());
        employeeProp.set(agreement.getEmployee());
        vehicleProp.set(agreement.getVehicle());
        startProp.set(agreement.getStart());
        endProp.set(agreement.getEnd());
        endpriceProp.set(agreement.getEndPrice());
    }

    @Override
    public int getfixedTerms() {
        return fixedTermsProp.get();
    }
    @Override
    public double getstartvalue() {
        return startValueProp.get();
    }
    @Override
    public Date getstartAgreement() {
        return startAgreementProp.get();
    }
    @Override
    public Rating getRki() {
        return RKiProp.get();
    }
    @Override
    public Customer getCustomer() {
        return customerProp.get();
    }
    @Override
    public Employee getEmployee() {
        return employeeProp.get();
    }
    public Vehicle getVehicle() {
        return vehicleProp.get();
    }
    @Override
    public Date getStart() {
        return startProp.get();
    }
    @Override
    public Date getEnd() {
        return endProp.get();
    }
    @Override
    public double getEndPrice() {
        return endpriceProp.get();
    }
    @Override
    public void setfixedTerms(int fixedterms) {
        fixedTermsProp.set(fixedterms);
    }
    @Override
    public void setStartValue(double startvalue) {
        startValueProp.set(startvalue);
    }
    @Override
    public void setstartAgreement(Date startagreement) {
        startAgreementProp.set(startagreement);
    }
    @Override
    public void setRki(Rating Rki) {
        RKiProp.set(Rki);
    }
    @Override
    public void setCustomer(Customer customer) {
        customerProp.set(customer);
    }
    @Override
    public void setEmployee(Employee employee) {
        employeeProp.set(employee);
    }
    public void setVehicle(Vehicle vehicle) {
        vehicleProp.set(vehicle);
    }
    @Override
    public void setStart(Date start) {
        startProp.set(start);
    }
    @Override
    public void setEnd(Date end) {
        endProp.set(end);
    }
    @Override
    public void setEndPrice(double endprice) {
        endpriceProp.set(endprice);
    }


    private void setup() {
        fixedTermsProp = new SimpleIntegerProperty();
        startValueProp = new SimpleDoubleProperty();
        startAgreementProp = new SimpleObjectProperty<>();
        RKiProp = new SimpleObjectProperty<>();
        customerProp = new SimpleObjectProperty<>();
        employeeProp = new SimpleObjectProperty<>();
        vehicleProp = new SimpleObjectProperty<>();
        startProp = new SimpleObjectProperty<>();
        endProp = new SimpleObjectProperty<>();
        endpriceProp = new SimpleDoubleProperty();
    }

    public IntegerProperty fixedTermsProperty() {
        return fixedTermsProp;
    }
    public DoubleProperty startValueProperty() {
        return startValueProp;
    }
    public ObjectProperty<Date> startAgreementProperty() {
        return startAgreementProp;
    }
    public ObjectProperty<Rating> RkiProperty() {
        return RKiProp;
    }
    public ObjectProperty<Customer> customerProperty() {
        return customerProp;
    }
    public ObjectProperty<Employee> employeeProperty() {
        return employeeProp;
    }
    public ObjectProperty<Vehicle> vehicleProperty() {
        return vehicleProp;
    }
    public ObjectProperty<Date> startProperty() {
        return startProp;
    }
    public ObjectProperty<Date> endProperty() {
        return endProp;
    }
    public DoubleProperty endPriceProperty() {
        return endpriceProp;
    }

    public static List<Object> makeMode(List<Agreement> agreements) {
        List<Object> models = new ArrayList<>();
        for(Agreement agreement : agreements)
            models.add(new ClosedAgreementsModel(agreement));
        return models;
    }
    public static List<Object> makeModelsAsObjects(List<Agreement> agreements) {
        List<Object> models = new ArrayList<>();
        for(Agreement agreement : agreements)
            models.add(new ClosedAgreementsModel(agreement));
        return models;
    }
}
