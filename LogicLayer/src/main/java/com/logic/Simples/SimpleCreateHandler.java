package com.logic.Simples;

import com.data.Data;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.handlers.HandlerObject;

public class SimpleCreateHandler extends HandlerObject {
    private Data data;
    public SimpleCreateHandler(Data data) {
        this.data = data;
    }
    public SimpleCreateHandler(Data data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Create;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.create(request.getObject()));
    }
}
