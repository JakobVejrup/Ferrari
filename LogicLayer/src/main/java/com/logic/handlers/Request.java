package com.logic.handlers;


import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.ActionParameter;
import com.model.threads.Validation;

public class Request {
    private Object object;
    private final CRUDType crud;
    private final ServiceType type;
    private ActionParameter setter;
    private Validation validation;
    public Request(ServiceType type, CRUDType crud) {
        this.crud = crud;
        this.type = type;
    }
    public Request(ServiceType type, CRUDType crud, Object object) {
        this(type, crud);
        this.object = object;
    }
    public Request(ServiceType type, CRUDType crud, ActionParameter setter) {
        this(type, crud);
        this.setter = setter;
    }
    public Request(ServiceType type, CRUDType crud, Object object, ActionParameter setter) {
        this(type, crud, object);
        this.setter = setter;
    }
    public Request(ServiceType type, CRUDType crud, Object object, Validation validation) {
        this(type, crud, object);
        this.validation = validation;
    }
    public Request(ServiceType type, CRUDType crud, Object object, ActionParameter setter, Validation validation) {
        this(type, crud, object, setter);
        this.validation = validation;
    }

    public CRUDType getCrud() {
        return crud;
    }

    public ActionParameter getSetter() {
        return setter;
    }

    public void setSetter(ActionParameter action) {
        setter = action;
    }
    public ServiceType getType() {
        return type;
    }
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
    public Validation getValidation() {
        return validation;
    }
    public boolean anyErrors() {
        return !validation.getMessages().isEmpty();
    }
}
