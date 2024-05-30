package com.model.threads;

import java.util.ArrayList;
//anders
public class Validation {
    private ArrayList<String> messages;
    private ActionParameter errorAction;


    public Validation() {
        messages = new ArrayList<>();
    }
    public Validation(ActionParameter errorAction) {
        this();
        this.errorAction = errorAction;
    }
    public void addMessage(String message) {
        messages.add(message);
    }
    public ArrayList<String> getMessages() {
        return messages;
    }
    public ActionParameter getErrorAction() {
        return errorAction;
    }
}
