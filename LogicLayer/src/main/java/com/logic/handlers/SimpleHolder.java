package com.logic.services.handlers;

import com.logic.services.Request;
import com.logic.services.handlers.HandlerHolder;

public class SimpleHolder extends HandlerHolder {
    public SimpleHolder(HandlerObject... handlers) {
        super(handlers);
    }

    @Override
    public void query(Request request) {
        root.query(request);
    }
}
