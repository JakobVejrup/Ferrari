package com.data.interfaces;

import com.model.entities.Employee;

//more than crud is needed
public interface UserExtra {
    public Employee login(Employee login);
    public Employee updateSelf(Employee update);
}
