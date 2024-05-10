package com.logic.validation.concretes;

import com.logic.handlers.HandlerObject;
import com.logic.handlers.Request;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;

public class AgreementValidation extends HandlerObject {
    public AgreementValidation() {
    }
    public AgreementValidation(HandlerObject handler) {
        setNext(handler);
    }
    @Override
    protected boolean check(Request request) {
        return request.getType() == ServiceType.Agreement;
    }

    @Override
    protected void action(Request request) {
        //validation example could be done in other classes which this will own
        Agreement agreement = (Agreement) request.getObject();
        if(agreement.getStartvalue() < 0)
            request.getValidation().addMessage("et lån skal starte over 0kr");
        if(agreement.getStartvalue() > agreement.getVehicle().getPrice())
            request.getValidation().addMessage("du kan ikke betale mere end bilens værdi");


    }
}
