package com.model.threads;

import java.util.ArrayList;

public class Validation {
    private ArrayList<String> messages;

    public Validation() {
        messages = new ArrayList<>();
    }
    public void addMessage(String message) {
        messages.add(message);
    }
    public ArrayList<String> getMessages() {
        return messages;
    }
}
