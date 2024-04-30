package com.model.entities;

import com.model.enums.EmployeeRank;

public class Employee extends Information {
    private EmployeeRank rank;
    private String password;

    public Employee(int id, String name, String phoneNumber, String email, EmployeeRank rank, String password) {
        super(id, name, phoneNumber, email);
        this.rank = rank;
        this.password = password;
    }
    public Employee() {}
    public EmployeeRank getRank() {
        return rank;
    }

    public void setRank(EmployeeRank rank) {
        this.rank = rank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
