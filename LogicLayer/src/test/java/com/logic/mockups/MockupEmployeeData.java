package com.logic.mockups;

import com.data.interfaces.Data;
import com.model.entities.Employee;

public class MockupEmployeeData implements Data {
    @Override
    public Object create(Object parameter) {
        Employee e = (Employee) parameter;
        e.setId(1);
        return e;
    }

    @Override
    public Object read(Object parameter) {
        return new MockupEmployee();
    }

    @Override
    public Object readAll(Object parameter) {
        return new MockupEmployee[] {new MockupEmployee(1), new MockupEmployee(2)};
    }

    @Override
    public Object update(Object parameter) {
        return new MockupEmployee(((int) parameter));
    }

    @Override
    public boolean delete(Object parameter) {
        return true;
    }
}
