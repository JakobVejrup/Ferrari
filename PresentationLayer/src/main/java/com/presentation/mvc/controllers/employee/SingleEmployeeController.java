package com.presentation.mvc.controllers.employee;

import com.model.entities.Employee;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.views.employee.SingleEmployeeView;
import javafx.scene.layout.Pane;

public class SingleEmployeeController implements Controller {
    private Employee model;
    private SingleEmployeeView view;

    public SingleEmployeeController(Employee model) {
        this.model = model;
        view = new SingleEmployeeView(model);
    }
    public void setModel(Employee model) {
        view.setModel(model);
    }
    @Override
    public Pane getView() {
        return view;
    }
    
}
