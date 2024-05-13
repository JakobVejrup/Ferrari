package com.logic.validation.concretes;

import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.ServiceType;
import com.model.entities.Customer;
//Magnus
public class CustomerValidation extends HandlerObject{
    public CustomerValidation() {
    }
    public CustomerValidation(HandlerObject handler) {
        setNext(handler);
    }

    @Override
    protected boolean check(Request request) {
        return request.getType() == ServiceType.Customer;
    }
    
    @Override
    protected void action(Request request) {
        Customer customer = (Customer) request.getObject();
        //Hvad skal definationen af en gyldig adresse være?
        if(customer.getAddress().length() < 5)
            request.getValidation().addMessage("Ikke en gyldig adresse");
        if(customer.getAddress().length() > 20)
            request.getValidation().addMessage("Ikke en gyldig adresse");
        if(customer.getCpr().length() != 10)
            request.getValidation().addMessage("Ikke et gyldigt cpr, skal være 10 cifre");
        if(customer.getCityZip().length() != 4)
            request.getValidation().addMessage("Ikke et gyldigt postnummer, skal være 4 cifre");
        //Ved ikke om jeg skal tilføje dem her:
        if(customer.getPhoneNumber().length() < 8)
            request.getValidation().addMessage("Ikke et gyldigt nr., skal være 8 cifre");
        if ((customer.getEmail().length() < 5) || (!customer.getEmail().contains("@")))
            request.getValidation().addMessage("Ikke en gyldig email");
        
        
    }
}
