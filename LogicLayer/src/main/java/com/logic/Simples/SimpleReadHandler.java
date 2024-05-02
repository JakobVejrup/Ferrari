package com.logic.Simples;

import com.data.Data;
import com.logic.handlers.HandlerObject;
import com.model.enums.CRUDType;
import com.model.threads.Request;

public class SimpleReadHandler extends HandlerObject {
    private Data data;
    public SimpleReadHandler(Data data) {
        this.data = data;
    }
    public SimpleReadHandler(Data data, HandlerObject handler) {
        this(data);
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getCrud() == CRUDType.Read;
    }

    @Override
    protected void action(Request request) {
        request.getSetter().action(data.read(request));
    }
}
