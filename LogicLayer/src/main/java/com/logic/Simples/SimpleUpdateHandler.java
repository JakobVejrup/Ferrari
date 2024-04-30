package com.logic.Simples;

import com.data.Data;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;

public class SimpleUpdateHandler extends HandlerObject {
    private Data data;
    public SimpleUpdateHandler(Data data) {
        this.data = data;
    }
    public SimpleUpdateHandler(Data data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Update;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.update(request.getObject()));
    }
}
