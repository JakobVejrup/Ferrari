package com.logic.services.employee;

import com.data.interfaces.UserExtra;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.model.entities.Employee;

public class CreateManagerHandler extends HandlerObject {
    private UserExtra data;
    public CreateManagerHandler(UserExtra data) {
        this.data = data;
    }
    public CreateManagerHandler(UserExtra data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.CreateManager;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.createManager((Employee)request.getObject()));
    }
}
