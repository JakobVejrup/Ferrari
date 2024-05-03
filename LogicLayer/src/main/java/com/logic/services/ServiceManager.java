package com.logic.services;

import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;

public class ServiceManager extends HandlerHolder {
    public ServiceManager(HandlerObject... handlers) {
        super(handlers);
    }
    @Override
    public void query(Request request) {
        if(request.getCrud() != null && request.getType() != null && root != null)
            root.query(request);
    }
}
