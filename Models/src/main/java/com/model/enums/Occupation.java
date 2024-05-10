package com.model.enums;

public enum Occupation {
    Manager("Chef"),
    Salesman("Sælger"),
    Admin("Administrator");
    private String title;
    private Occupation(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

