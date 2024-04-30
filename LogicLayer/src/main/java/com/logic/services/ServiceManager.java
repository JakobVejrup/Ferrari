package com.logic.services;

import com.logic.services.handlers.HandlerHolder;
import com.logic.services.handlers.HandlerObject;

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
