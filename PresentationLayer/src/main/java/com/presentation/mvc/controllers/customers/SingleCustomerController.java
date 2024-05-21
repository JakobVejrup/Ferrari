package com.presentation.mvc.controllers.customers;

import com.model.entities.Customer;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.views.customer.SingleCustomerView;
import javafx.scene.layout.Pane;

public class SingleCustomerController implements Controller{
    private Customer model;
    private SingleCustomerView view;

    public SingleCustomerController(Customer model) {
        this.model = model;
        view = new SingleCustomerView(model);
    }
    public void setModel(Customer model) {
        view.setModel(model);
    }
    @Override
    public Pane getView() {
        return view;
    }
}
