package com.presentation.mvc.models.employees;

import com.model.entities.Employee;
import com.model.enums.Occupation;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EmployeeModel {
    private Employee employee;
    private StringProperty email;
    private StringProperty name;
    private ObjectProperty<Occupation> occupation;
    private StringProperty phoneNumber;
    private DoubleProperty loanLimit;
    private StringProperty password;
    public EmployeeModel() {
        employee = new Employee();
        setup();
    }
    public EmployeeModel(Employee employee) {
        this.employee = employee;
        setup();
    }
    public void setup() {
        name = new SimpleStringProperty();
        name.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                employee.setName(newValue);                
            }
        });
        email = new SimpleStringProperty();
        email.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                employee.setEmail(newValue);     
            }
        });

        phoneNumber = new SimpleStringProperty();
        phoneNumber.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                employee.setPhoneNumber(newValue);  
            }
        });
        password = new SimpleStringProperty();
        password.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                employee.setPassword(newValue);
            }
        });
        occupation = new SimpleObjectProperty<Occupation>();
        occupation.addListener(new ChangeListener<Occupation>() {
            @Override
            public void changed(ObservableValue<? extends Occupation> observable, Occupation oldValue, Occupation newValue) {
                employee.setOccupation(newValue);
            }
        });
        loanLimit = new SimpleDoubleProperty();
        loanLimit.addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                employee.setLoanLimit((Double)newValue);
            }
        });
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

