package com.presentation.mvc.models.agreements;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.rki.rki.Rating;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public class OpenAgreementsModel extends Agreement{
    private IntegerProperty fixedTermsProp;
    private DoubleProperty startValueProp;
    private ObjectProperty<Date> startAgreementProp;
    private ObjectProperty<Date> endAgreementProp;
    private ObjectProperty<Rating> RKiProp;
    private ObjectProperty<Customer> customerProp;
    private ObjectProperty<Employee> employeeProp;
    private ObjectProperty<Vehicle> vehicleProp;
    private ObjectProperty<List<Invoice>> invoicesProp;
    private DoubleProperty daysRateProp;
    public OpenAgreementsModel() {
        fixedTermsProp = new SimpleIntegerProperty();
        startValueProp = new SimpleDoubleProperty();
        startAgreementProp = new SimpleObjectProperty<Date>();
        RKiProp = new SimpleObjectProperty<Rating>();
        customerProp = new SimpleObjectProperty<Customer>();
        employeeProp = new SimpleObjectProperty<Employee>();
        vehicleProp = new SimpleObjectProperty<Vehicle>();
        invoicesProp = new SimpleObjectProperty<List<Invoice>>();
        daysRateProp = new SimpleDoubleProperty();
        setCustomer(new Customer());
        setEmployee(new Employee());
        setVehicle(new Vehicle());
    }

    public OpenAgreementsModel(Agreement agreement) {
        this();
        setId(agreement.getId());
        fixedTermsProp.set(agreement.getFixedTerms());
        startValueProp.set(agreement.getStartValue());
        startAgreementProp.set(agreement.getStartAgreement());
        RKiProp.set(agreement.getRki());
        setCustomer(agreement.getCustomer());
        setEmployee(agreement.getEmployee());
        setVehicle(agreement.getVehicle());
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
    public double getDaysRate() {
        return daysRateProp.get();
    }
    public Date getStart() {
        return startAgreementProp.get();
    }

    public void setStart(Date start) {
        startAgreementProp.set(start);
    }

    public Date getEnd() {
        return endAgreementProp.get();
    }

    public void setEnd(Date end) {
        endAgreementProp.set(end);
    }
    @Override
    public void setDaysRate(double daysRate) {
        daysRateProp.set(daysRate);
    }
    @Override
    public void setCustomer(Customer customer) {
        if(customer != null)
            customerProp.set(customer);
        else
            customerProp.set(new Customer());

    }
    @Override
    public void setEmployee(Employee employee) {
        if(employee != null)
            employeeProp.set(employee);
        else
            employeeProp.set(new Employee());
    }
    @Override
    public void setVehicle(Vehicle vehicle) {
        if(vehicle != null)
        vehicleProp.set(vehicle);
    else
        vehicleProp.set(new Vehicle());
    }
    public void setPayments(List<Invoice> payments) {
        if(payments != null)
        invoicesProp.set(payments);
    }
    public List<Invoice> getPayments() {
        return invoicesProp.get();
    }
    public ObjectProperty<List<Invoice>> invoiceProperty() {
        return invoicesProp;
    }
    public IntegerProperty fixedTermsProperty() {
        return fixedTermsProp;
    }
    public DoubleProperty startValueProperty() {
        return startValueProp;
    }
    public DoubleProperty daysRateProperty() {
        return daysRateProp;
    }
    public ObjectProperty<Date> startAgreementProperty() {
        return startAgreementProp;
    }
    public ObjectProperty<Date> endAgreementProperty() {
        return endAgreementProp;
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
