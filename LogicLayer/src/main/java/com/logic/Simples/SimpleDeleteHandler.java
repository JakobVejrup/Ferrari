package com.logic.Simples;

import com.data.interfaces.Data;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;

public class SimpleDeleteHandler extends HandlerObject {
    private Data data;

    public SimpleDeleteHandler(Data data) {
        this.data = data;

    }
    public SimpleDeleteHandler(Data data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Delete;
    }

    @Override
    protected void action(Request request) {
        if(request.getSetter() != null)
            request.getSetter().action(data.delete(request.getObject()));
        else 
            data.delete(request.getObject());
    }
}
