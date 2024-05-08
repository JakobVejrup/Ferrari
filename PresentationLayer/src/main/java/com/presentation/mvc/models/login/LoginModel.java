package com.presentation.mvc.models.login;

import com.model.entities.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {
    private StringProperty email;
    private StringProperty password;

    public LoginModel() {
        email = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
