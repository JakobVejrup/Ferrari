package com.logic.mockups;

import com.model.entities.Employee;
import com.model.enums.Occupation;
//anders
//easy to use employee only test id
public class MockupEmployee extends Employee {
    public MockupEmployee() {
        super(0, "Test", "Test", "Test", Occupation.Salesman);
    }
    public MockupEmployee(int id) {
        super(id, "Test", "Test", "Test", Occupation.Salesman);
    }
    @Override
    public boolean equals(Object other) {
        return ((Employee)other).getId() == getId();
    }
}
