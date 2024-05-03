package com.logic.services.employee;

import com.data.interfaces.Data;
import com.data.interfaces.UserExtra;
import com.logic.Simples.*;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.handlers.SimpleHolder;
import com.logic.services.enums.ServiceType;


public class EmployeeService extends HandlerObject {
    private HandlerHolder holder;

    public EmployeeService(Data data, UserExtra extra) {
        //creates all handlers and adds them to each other
        holder = new SimpleHolder(
                new SimpleCreateHandler(data),
                new SimpleReadHandler(data),
                new SimpleReadAllHandler(data),
                new SimpleUpdateHandler(data),
                new SimpleDeleteHandler(data),
                new LoginHandler(extra),
                new UpdateSelfHandler(extra)
        );
    }

    @Override
    public boolean check(Request request) {
        return request.getType() == ServiceType.Employee;
    }

    @Override
    public void action(Request request) {
        // go down new COR with CRUD
        holder.query(request);
    }
}
