package com.logic.services.customer;

import com.data.interfaces.Data;
import com.logic.Simples.*;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.handlers.SimpleHolder;
import com.logic.services.employee.UpdateSelfHandler;
import com.logic.services.enums.ServiceType;

// magnus
public class CustomerService extends HandlerObject {
    private HandlerHolder holder;

    public CustomerService(Data data) {
        // initialiserer holder med de forskellige handlers
        holder = new SimpleHolder(
                new SimpleCreateHandler(data),
                new SimpleReadHandler(data),
                new SimpleReadAllHandler(data),
                new SimpleUpdateHandler(data),
                new SimpleDeleteHandler(data)
               
        );
    }

    // metode til at tjekke om request er en kunde
    @Override
    public boolean check(Request request) {
        return request.getType() == ServiceType.Customer;
    }

    // metode til at udføre handlinger på en kunde baeret på request
    @Override
    public void action(Request request) {
        holder.query(request);
    }
}