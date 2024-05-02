package com.logic.handlers;

import com.model.threads.Request;

public class SimpleHolder extends HandlerHolder {
    public SimpleHolder(HandlerObject... handlers) {
        super(handlers);
    }

    @Override
    public void query(Request request) {
        root.query(request);
    }
}
