package com.presentation.mvc.controllers.customers;

import com.model.entities.Customer;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.views.customer.SingleCustomerView;
import javafx.scene.control.Button;

public class SingleCustomerController extends Controller{
    private Customer model;
    private SingleCustomerView view;
    public SingleCustomerController(Customer model) {
        this.model = model;
        view = new SingleCustomerView(model);
        setView(view);
    }
    public void setModel(Customer model) {
        view.setModel(model);
    }
    public void addButtons(Button... buttons) {
        view.addButtons(buttons);
    }
}
