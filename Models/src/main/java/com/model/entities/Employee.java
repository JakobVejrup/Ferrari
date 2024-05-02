package com.model.entities;

import com.model.enums.Occupation;

public class Employee extends Information {
    private Occupation rank;
    private String password;

    public Employee(int id, String name, String phoneNumber, String email, Occupation rank, String password) {
        super(id, name, phoneNumber, email);
        this.rank = rank;
        this.password = password;
    }
    public Employee() {}
    public Occupation getRank() {
        return rank;
    }

    public void setRank(Occupation rank) {
        this.rank = rank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
