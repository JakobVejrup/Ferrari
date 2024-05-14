package com.presentation.mvc.models.employees;

import java.util.ArrayList;
import java.util.List;

import com.model.entities.Employee;
import com.model.enums.Occupation;
import javafx.beans.property.*;

public class EmployeeModel extends Employee{
    private StringProperty emailProp;
    private StringProperty nameProp;
    private ObjectProperty<Occupation> occupationProp;
    private StringProperty phoneNumberProp;
    private DoubleProperty loanLimitProp;
    private StringProperty passwordProp;

    public EmployeeModel() {
        setup();
    }

    public EmployeeModel(Employee employee) {
        setup();
        setId(employee.getId());
        emailProp.set(employee.getEmail());
        nameProp.set(employee.getName());
        occupationProp.set(employee.getOccupation());
        phoneNumberProp.set(employee.getPhoneNumber());
        loanLimitProp.set(employee.getLoanLimit());
        passwordProp.set(employee.getPassword());
    }

    @Override
    public String getName() {
        return nameProp.get();
    }
    @Override
    public String getEmail() {
        return emailProp.get();
    }
    @Override
    public String getPhoneNumber() {
        return phoneNumberProp.get();
    }
    @Override
    public String getPassword() {
        return passwordProp.get();
    }
    @Override
    public Occupation getOccupation() {
        return occupationProp.get();
    }
    @Override
    public double getLoanLimit() {
        return loanLimitProp.get();
    }
    @Override
    public void setName(String name) {
        nameProp.set(name);
    }
    @Override
    public void setEmail(String email) {
        emailProp.set(email);
    }
    @Override
    public void setPhoneNumber(String number) {
        phoneNumberProp.set(number);
    }
    @Override
    public void setPassword(String password) {
        passwordProp.set(password);
    }
    @Override
    public void setOccupation(Occupation occupation) {
        occupationProp.set(occupation);
    }
    @Override
    public void setLoanLimit(Double limit) {
        loanLimitProp.set(limit);
    }
    public void setup() {
        nameProp = new SimpleStringProperty();
        emailProp = new SimpleStringProperty();
        phoneNumberProp = new SimpleStringProperty();
        passwordProp = new SimpleStringProperty();
        occupationProp = new SimpleObjectProperty<Occupation>();
        loanLimitProp = new SimpleDoubleProperty();
    }

    public StringProperty emailProperty() {
        return emailProp;
    }

    public ObjectProperty<Occupation> occupationProperty() {
        return occupationProp;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumberProp;
    }

    public DoubleProperty loanLimitProperty() {
        return loanLimitProp;
    }

    public StringProperty passwordProperty() {
        return passwordProp;
    }

    public StringProperty nameProperty() {
        return nameProp;
    }

    public static List<EmployeeModel> makeModels(List<Employee> employees) {
        List<EmployeeModel> models = new ArrayList<>();
        for (Employee employee : employees)
            models.add(new EmployeeModel(employee));
        return models;
    }
    
    public static List<Object> makeModelsAsObjects(List<Employee> employees) {
        List<Object> models = new ArrayList<>();
        for(Employee employee : employees)
            models.add(new EmployeeModel(employee));
        return models;
    } 
}

