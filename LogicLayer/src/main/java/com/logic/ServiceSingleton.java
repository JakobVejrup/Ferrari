package com.logic;

import com.logic.services.ServiceManager;
import com.logic.services.agreements.AgreementClosedService;
import com.logic.services.agreements.AgreementOpenService;
import com.logic.services.duedatepayment.DueDatePaymentService;
import com.logic.services.employee.EmployeeService;
import com.data.ConnectionData;
import com.data.dao.AgreementClosedData;
import com.data.dao.AgreementOpenData;
import com.data.dao.EmployeeData;
import com.data.dao.DuePaymentData;
import com.logic.handlers.Handler;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.Request;
import com.logic.validation.ValidationManager;
import com.logic.validation.concretes.AgreementValidation;
import com.logic.validation.concretes.EmployeeValidation;

public class ServiceSingleton implements Handler {
    private static ServiceSingleton instance;
    private HandlerHolder validations;
    private HandlerHolder services;
    private ServiceSingleton() {
        ConnectionData db = new ConnectionData();
        EmployeeData employeeData = new EmployeeData(db);
        AgreementClosedData agreementClosed = new AgreementClosedData(db);
        DuePaymentData paymentData = new DuePaymentData(db);
        AgreementOpenData agreementOpen = new AgreementOpenData(db);


        validations = new ValidationManager(
            new EmployeeValidation(employeeData), 
            new AgreementValidation()
    
        );
        services = new ServiceManager(
            new EmployeeService(employeeData, employeeData),
            new AgreementOpenService(agreementOpen),
            new DueDatePaymentService(paymentData),
            new AgreementClosedService(agreementClosed)
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
                if(request.getValidation() != null) {
                    validations.query(request);
                    if(request.anyErrors()) {
                        request.getValidation().getErrorAction().action(request);
                        return;
                    }
                }
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
