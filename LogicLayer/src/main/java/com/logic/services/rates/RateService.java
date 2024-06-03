package com.logic.services.rates;

import com.data.interfaces.Data;
import com.logic.Simples.SimpleReadHandler;
import com.logic.handlers.HandlerHolder;
import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.handlers.SimpleHolder;
import com.logic.services.enums.ServiceType;
//anders
public class RateService extends HandlerObject {
    private HandlerHolder holder;

    public RateService(Data data) {
        //creates all handlers and adds them to each other
        holder = new SimpleHolder(
                new SimpleReadHandler(data)
        );
    }

    @Override
    public boolean check(Request request) {
        return request.getType() == ServiceType.Rate;
    }

    @Override
    public void action(Request request) {
        // go down new COR with CRUD
        holder.query(request);
    }
}
