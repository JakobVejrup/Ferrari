package com.data.interfaces;

import com.model.entities.Employee;
// anders
public interface UserExtra {
    public Employee login(Employee login);
    public Employee updateSelf(Employee update);
}
