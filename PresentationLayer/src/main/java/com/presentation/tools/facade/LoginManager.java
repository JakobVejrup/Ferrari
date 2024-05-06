package com.presentation.tools.facade;

import com.model.entities.Employee;

public interface LoginManager {
    public void login (Employee employee);
    public Employee getLoggedIn();
}
