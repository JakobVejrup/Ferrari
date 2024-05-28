package com.presentation.mvc.models.customer;

import java.util.ArrayList;
import java.util.List;

import com.model.entities.City;
import com.model.entities.Customer;

import javafx.beans.property.*;



public class CustomerModel extends Customer{
    private StringProperty nameProp;
    private StringProperty emailProp;
    private StringProperty phoneNumberProp;
    private StringProperty addressProp;
    private ObjectProperty<City> cityProp;
    private StringProperty CprProp;
    private boolean empty;
    public CustomerModel(boolean empty) {
        setup();
        this.empty = empty;
    }
    public CustomerModel() {
        setup();
        empty = false;
    }

    public CustomerModel(Customer customer) {
        setup();
        setId(customer.getId());
        nameProp.set(customer.getName());
        emailProp.set(customer.getEmail());
        phoneNumberProp.set(customer.getPhoneNumber());
        addressProp.set(customer.getAddress());
        cityProp.set(customer.getCity());
        CprProp.set(customer.getCpr());
    }
    public boolean getEmpty() {
        return empty;
    }
    @Override
    public String getName() {
        return nameProp.get();
    }
    @Override
    public String getEmail() {
        return emailProp.get();
    }
    @Override
    public String getPhoneNumber() {
        return phoneNumberProp.get();
    }
    @Override
    public String getAddress() {
        return addressProp.get();
    }
    @Override
    public City getCity() {
        return cityProp.get();
    }
    @Override
    public String getCpr() {
        return CprProp.get();
    }
    @Override
    public void setName(String name) {
        nameProp.set(name);
    }
    @Override
    public void setEmail(String email) {
        emailProp.set(email);
    }
    @Override
    public void setPhoneNumber(String number) {
        phoneNumberProp.set(number);
    }
    @Override
    public void setAddress(String address) {
        addressProp.set(address);
    }
    @Override
    public void setCity(City city) {
        cityProp.set(city);
    }
    @Override
    public void setCpr(String cpr) {
        CprProp.set(cpr);
    }
    public void setup() {
        nameProp = new SimpleStringProperty("");
        emailProp = new SimpleStringProperty("");
        phoneNumberProp = new SimpleStringProperty("");
        addressProp = new SimpleStringProperty("");
        cityProp = new SimpleObjectProperty<City>(new City());
        CprProp = new SimpleStringProperty("");
    }
    public StringProperty nameProperty() {
        return nameProp;
    }
    public StringProperty emailProperty() {
        return emailProp;
    }
    public StringProperty phoneNumberProperty() {
        return phoneNumberProp;
    }
    public StringProperty addressProperty() {
        return addressProp;
    }
    public ObjectProperty<City> cityProperty() {
        return cityProp;
    }
    public StringProperty CprProperty() {
        return CprProp;
    }

    public static List<CustomerModel> MakeModel(List<Customer> customers) {
        List<CustomerModel> models = new ArrayList<>();
        for(Customer customer : customers) 
            models.add(new CustomerModel(customer));
        return models;
    }

    public static List<Object> makeModelsAsObjects(List<Customer> customers) {
        List<Object> models = new ArrayList<>();
        for(Customer customer : customers) 
            models.add(new CustomerModel(customer));
        return models;
    }
    public void unbindAll() {
        nameProp.unbind();
        emailProp.unbind();
        phoneNumberProp.unbind();
        addressProp.unbind();
        cityProp.unbind();
        CprProp.unbind();
    }
}
