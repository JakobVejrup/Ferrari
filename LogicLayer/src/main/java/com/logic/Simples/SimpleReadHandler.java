package com.logic.Simples;

import com.data.Data;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;

public class SimpleReadAllHandler extends HandlerObject {
    private Data data;
    private CRUDType type;
    public SimpleReadAllHandler(Data data, CRUDType type) {
        this.data = data;
        this.type = type;
    }
    public SimpleReadAllHandler(Data data, CRUDType type, HandlerObject handler) {
        this(data, type);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == type;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.create(request.getObject()));
    }
}
