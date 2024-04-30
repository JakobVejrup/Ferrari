package com.logic.validation.concretes;

import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;

public class EmployeeValidation extends HandlerObject {
    public EmployeeValidation() {
    }
    public EmployeeValidation(HandlerObject handler) {
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getType() == ServiceType.Employee;
    }

    @Override
    protected void action(Request request) {
        //validation example could be done in other classes which this will own
        Employee employee = (Employee) request.getObject();
        if(employee.getName().length() < 5)
            request.getValidation().addMessage("navn er tomt, minimum 5 bogstaver");
        if(employee.getName().length() > 40)
            request.getValidation().addMessage("navn er for langt");
    }}
