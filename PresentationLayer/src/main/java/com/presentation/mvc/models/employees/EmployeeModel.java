package com.presentation.mvc.models.employees;

import java.util.ArrayList;
import java.util.List;

import com.model.entities.Employee;
import com.model.enums.Occupation;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EmployeeModel extends Employee{
    private StringProperty emailProp;
    private StringProperty nameProp;
    private ObjectProperty<Occupation> occupationProp;
    private StringProperty phoneNumberProp;
    private DoubleProperty loanLimitProp;
    private StringProperty passwordProp;

    public EmployeeModel() {
        super();
        setup();
    }

    public EmployeeModel(Employee employee) {
        super();
        setup();
        emailProp.set(employee.getEmail());
        nameProp.set(employee.getName());
        occupationProp.set(employee.getOccupation());
        phoneNumberProp.set(employee.getPhoneNumber());
        loanLimitProp.set(employee.getLoanLimit());
        passwordProp.set(employee.getPassword());
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        nameProp.set(name);
    }
    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        emailProp.set(email);
    }
    @Override
    public void setPhoneNumber(String number) {
        super.setPhoneNumber(number);
        phoneNumberProp.set(number);
    }
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        passwordProp.set(password);
    }
    @Override
    public void setOccupation(Occupation occupation) {
        super.setOccupation(occupation);
        occupationProp.set(occupation);
    }
    @Override
    public void setLoanLimit(Double limit) {
        super.setLoanLimit(limit);
        loanLimitProp.set(limit);
    }
    public void setup() {
        nameProp = new SimpleStringProperty();
        nameProp.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setName(newValue);                
            }
        });
        emailProp = new SimpleStringProperty();
        emailProp.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setEmail(newValue);     
            }
        });

        phoneNumberProp = new SimpleStringProperty();
        phoneNumberProp.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setPhoneNumber(newValue);  
            }
        });
        passwordProp = new SimpleStringProperty();
        passwordProp.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setPassword(newValue);
            }
        });
        occupationProp = new SimpleObjectProperty<Occupation>();
        occupationProp.addListener(new ChangeListener<Occupation>() {
            @Override
            public void changed(ObservableValue<? extends Occupation> observable, Occupation oldValue, Occupation newValue) {
                setOccupation(newValue);
            }
        });
        loanLimitProp = new SimpleDoubleProperty();
        loanLimitProp.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setLoanLimit((Double)newValue);
            }
        });
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

