package com.logic.validation;

import com.logic.services.Request;
import com.logic.services.handlers.HandlerHolder;
import com.logic.services.handlers.HandlerObject;

public class ValidationManager extends HandlerHolder {
    public ValidationManager(HandlerObject... handlers) {
        super(handlers);
    }

    @Override
    public void query(Request request) {
        if(root != null && request.getObject() != null)
            root.query(request);
    }
}
