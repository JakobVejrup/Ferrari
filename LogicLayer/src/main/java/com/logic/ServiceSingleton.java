package com.logic;

import com.logic.services.ServiceManager;
import com.logic.services.agreements.AgreementClosedService;
import com.logic.services.agreements.AgreementOpenService;
import com.logic.services.city.CityService;
import com.logic.services.customer.CustomerService;
import com.logic.services.employee.EmployeeService;
import com.logic.services.invoice.InvoiceService;
import com.logic.services.rates.RateService;
import com.logic.services.vehicle.VehicleService;
import com.data.ConnectionData;
import com.data.dao.AgreementClosedData;
import com.data.dao.AgreementOpenData;
import com.data.dao.CityData;
import com.data.dao.CustomerData;
import com.data.dao.EmployeeData;
import com.data.dao.InvoiceData;
import com.data.dao.RatesData;
import com.data.dao.VehicleData;
import com.logic.handlers.Handler;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.Request;
import com.logic.validation.ValidationManager;
import com.logic.validation.concretes.AgreementValidation;
import com.logic.validation.concretes.EmployeeValidation;
//anders
public class ServiceSingleton implements Handler {
    private static ServiceSingleton instance;
    private HandlerHolder validations;
    private HandlerHolder services;
    private ServiceSingleton() {
        ConnectionData db = new ConnectionData();
        EmployeeData employeeData = new EmployeeData(db);
        CustomerData customerData = new CustomerData(db);
        InvoiceData invoiceData = new InvoiceData(db);
        VehicleData vehicleData = new VehicleData(db);
        CityData cityData = new CityData(db);
        RatesData rateData = new RatesData(db);
        AgreementClosedData agreementClosed = new AgreementClosedData(db, invoiceData, customerData, employeeData);
        AgreementOpenData agreementOpen = new AgreementOpenData(db, invoiceData, customerData, employeeData);

        validations = new ValidationManager(
            new EmployeeValidation(employeeData), 
            new AgreementValidation()
    
        );
        services = new ServiceManager(
            new EmployeeService(employeeData, employeeData),
            new RateService(rateData),
            new VehicleService(vehicleData),
            new AgreementOpenService(agreementOpen),
            new InvoiceService(invoiceData),
            new AgreementClosedService(agreementClosed),
            new CustomerService(customerData),
            new CityService(cityData)
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
    public Object queryNoThread(Request request) {
        final Object[] returnValue = new Object[1];
        request.setSetter((value) -> returnValue[0] = value);
        services.query(request);
        return returnValue[0];
    }
    public static ServiceSingleton getInstance() {
        return instance == null ? instance = new ServiceSingleton() : instance;
    }
}
