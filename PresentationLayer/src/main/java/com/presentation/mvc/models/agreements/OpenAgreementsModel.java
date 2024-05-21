package com.presentation.mvc.models.agreements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.rki.rki.Rating;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class OpenAgreementsModel extends Agreement{
    private IntegerProperty fixedTermsProp;
    private DoubleProperty startValueProp;
    private ObjectProperty<Date> startAgreementProp;
    private ObjectProperty<Rating> RKiProp;
    private ObjectProperty<Customer> customerProp;
    private ObjectProperty<Employee> employeeProp;
    private ObjectProperty<Vehicle> vehicleProp;
    public OpenAgreementsModel() {
        fixedTermsProp = new SimpleIntegerProperty();
        startValueProp = new SimpleDoubleProperty();
        startAgreementProp = new SimpleObjectProperty<Date>();
        RKiProp = new SimpleObjectProperty<Rating>();
        customerProp = new SimpleObjectProperty<Customer>();
        employeeProp = new SimpleObjectProperty<Employee>();
        vehicleProp = new SimpleObjectProperty<Vehicle>();
    }

    public OpenAgreementsModel(Agreement agreement) {
        this();
        setId(agreement.getId());
        fixedTermsProp.set(agreement.getFixedTerms());
        startValueProp.set(agreement.getStartValue());
        startAgreementProp.set(agreement.getStartAgreement());
        RKiProp.set(agreement.getRki());
        customerProp.set(agreement.getCustomer());
        employeeProp.set(agreement.getEmployee());
        vehicleProp.set(agreement.getVehicle());
        
    }

    @Override
    public int getFixedTerms() {
        return fixedTermsProp.get();
    }
    @Override
    public double getStartValue() {
        return startValueProp.get();
    }
    @Override
    public Date getStartAgreement() {
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
    public void setFixedTerms(int fixedterms) {
        fixedTermsProp.set(fixedterms);
    }
    @Override
    public void setStartValue(double startvalue) {
        startValueProp.set(startvalue);
    }
    @Override
    public void setStartAgreement(Date startagreement) {
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

    public IntegerProperty fixedTermsProperty() {
        return fixedTermsProp;
    }
    public DoubleProperty startValueProperty() {
        return startValueProp;
    }
    public ObjectProperty<Date> startAgreementProperty() {
        return startAgreementProp;
    }
    public ObjectProperty<Rating> RKiProperty() {
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
    public static List<OpenAgreementsModel> makeModels(List<Agreement> agreements) {
        List<OpenAgreementsModel> models = new ArrayList<>();
        for (Agreement agreement : agreements)
            models.add(new OpenAgreementsModel(agreement));
        return models;
    }
    public static List<Object> makeModelsAsObjects(List<Agreement> agreements) {
        List<Object> models = new ArrayList<>();
        for(Agreement agreement : agreements)
            models.add(new OpenAgreementsModel(agreement));
        return models;
    }
//tjek
        
}
