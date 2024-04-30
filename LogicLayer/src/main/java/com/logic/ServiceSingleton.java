package com.logic;

import com.logic.services.Request;
import com.logic.services.ServiceManager;
import com.logic.services.handlers.Handler;
import com.logic.services.handlers.HandlerHolder;
import com.logic.validation.ValidationManager;

public class ServiceSingleton implements Handler {
    private static ServiceSingleton instance;
    private HandlerHolder validations;
    private HandlerHolder services;
    private ServiceSingleton() {
        validations = new ValidationManager(
        );
        services = new ServiceManager(
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
