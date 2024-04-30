package com.logic.validation;

import com.logic.handlers.Request;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;

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
