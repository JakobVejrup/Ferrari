package com.logic;

import com.logic.services.ServiceManager;
import com.logic.services.agreements.AgreementService;
import com.logic.services.employee.EmployeeService;
import com.data.SQLData;
import com.data.dao.AgreementData;
import com.data.dao.EmployeeData;
import com.logic.handlers.Handler;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.Request;
import com.logic.validation.ValidationManager;
import com.logic.validation.concretes.EmployeeValidation;

public class ServiceSingleton implements Handler {
    private static ServiceSingleton instance;
    private HandlerHolder validations;
    private HandlerHolder services;
    private ServiceSingleton() {
        SQLData db = new SQLData();
        EmployeeData employeeData = new EmployeeData(db);
        AgreementData agreementData = new AgreementData(db);
        validations = new ValidationManager(
            new EmployeeValidation()
        );
        services = new ServiceManager(
            new EmployeeService(employeeData, employeeData),
            new AgreementService(agreementData)
        );
    }
    public void setValidations(HandlerHolder validations) {
        this.validations = validations;
    }
    public void setServices(HandlerHolder services) {
        this.services = services;
    }
    // query will run validations and see if its goooood, if that's the case it will go to services and get the sql data which will go in the setter methods parameter inside SQLRequest
    @Override
    public void query(Request request) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if(request.getObject() != null)
                    validations.query(request);
                if(!request.anyErrors())
                    services.query(request);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    public static ServiceSingleton getInstance() {
        return instance == null ? instance = new ServiceSingleton() : instance;
    }
}
