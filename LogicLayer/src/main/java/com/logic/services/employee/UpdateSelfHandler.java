package com.logic.services.employee;

import com.data.interfaces.UserExtra;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.model.entities.Employee;

public class UpdateSelfHandler extends HandlerObject {
    private UserExtra data;
    public UpdateSelfHandler(UserExtra data) {
        this.data = data;
    }
    public UpdateSelfHandler(UserExtra data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.UpdateSelf;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.updateSelf((Employee)request.getObject()));
    }
}
