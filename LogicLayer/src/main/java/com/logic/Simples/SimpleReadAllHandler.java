package com.logic.Simples;

import com.data.interfaces.Data;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;

public class SimpleReadAllHandler extends HandlerObject {
    private Data data;
    public SimpleReadAllHandler(Data data) {
        this.data = data;
    }
    public SimpleReadAllHandler(Data data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.ReadAll;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.readAll(request.getObject()));
    }
}
