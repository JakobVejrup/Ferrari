package com.logic.Simples;

import com.data.interfaces.Data;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
//anders
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
        if(request.getSetter() != null)
            request.getSetter().action(data.create(request.getObject()));
        else 
            data.create(request.getObject());
    }
}
