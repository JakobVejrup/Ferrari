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
    private boolean mustValidate;
    public Request(ServiceType type, CRUDType crud) {
        this.crud = crud;
        this.type = type;
        validation = new Validation();
    }
    public Request(ServiceType type, CRUDType crud, boolean mustValidate) {
        this(type, crud);
        this.mustValidate = mustValidate;
        validation = new Validation();
    }
    public Request(ServiceType type, CRUDType crud, boolean mustValidate, Object object) {
        this(type, crud, mustValidate);
        this.object = object;
    }
    public Request(ServiceType type, CRUDType crud, boolean mustValidate, Object object, ActionParameter setter) {
        this(type, crud, mustValidate, object);
        this.setter = setter;
    }
    public Request(ServiceType type, CRUDType crud, boolean mustValidate, ActionParameter setter) {
        this(type, crud, mustValidate);
        this.setter = setter;
    }

    public boolean getMustValidate() {
        return mustValidate;
    }

    public CRUDType getCrud() {
        return crud;
    }

    public ActionParameter getSetter() {
        return setter;
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
