package com.presentation.mvc.models.modals.employee;

import com.model.entities.Employee;
import com.model.enums.Occupation;
import javafx.beans.property.*;

public class CreateEmployeeModel {
    private Employee employee;
    private StringProperty email;
    private StringProperty name;
    private ObjectProperty<Occupation> occupation;
    private StringProperty phoneNumber;
    private DoubleProperty loanLimit;
    private StringProperty password;
    public CreateEmployeeModel() {
        employee = new Employee();
        name = new SimpleStringProperty() {
            @Override
            public void set(String newValue) {
                super.set(newValue);
                employee.setName(newValue);
            }
        };
        email = new SimpleStringProperty(){
            @Override
            public void set(String newValue) {
                super.set(newValue);
                employee.setEmail(newValue);
            }
        };
        phoneNumber = new SimpleStringProperty(){
            @Override
            public void set(String newValue) {
                super.set(newValue);
                employee.setPhoneNumber(newValue);
            }
        };
        password = new SimpleStringProperty(){
            @Override
            public void set(String newValue) {
                super.set(newValue);
                employee.setPassword(newValue);
            }
        };
        occupation = new SimpleObjectProperty<Occupation>(){
            @Override
            public void set(Occupation newValue) {
                super.set(newValue);
                employee.setOccupation(newValue);
            }
        };
        loanLimit = new SimpleDoubleProperty(){
            @Override
            public void set(double newValue) {
                super.set(newValue);
                employee.setLoanLimit(newValue);
            }
        };
    }
    public Employee getEmployee() {
        return employee;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public Occupation getOccupation() {
        return occupation.get();
    }

    public ObjectProperty<Occupation> occupationProperty() {
        return occupation;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public double getLoanLimit() {
        return loanLimit.get();
    }

    public DoubleProperty loanLimitProperty() {
        return loanLimit;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
}

