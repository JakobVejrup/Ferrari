package com.presentation.mvc.controllers.login;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.controllers.modals.employee.CreateEmployeeController;
import com.presentation.mvc.models.login.LoginView;
import com.presentation.mvc.views.login.LoginModel;
import com.presentation.tools.facade.Facade;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {
    private LoginView view;
    private LoginModel model;
    public LoginController() {
        model = new LoginModel();
        // needs a eventhandler which is a funtional interface type
        view = new LoginView(model, this::login);
    }

    public void login(ActionEvent event) {
        Employee employee = new Employee();
        employee.setEmail(model.getEmail());
        employee.setPassword(model.getPassword());
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.Login, false,
                employee,
                this::loginAction
        ));
    }
    public LoginView getView() {
        return view;
    }
    public void loginAction(Object login) {
        if(login == null)
            return;
        Employee employee = (Employee) login;
        Facade.getInstance().login(employee);
        //set employee to the login in some sort of manager object
        //Go further in UI MVC style incoming
    }

}
