package com.presentation.mvc.controllers.login;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.presentation.mvc.controllers.leftnavbar.LeftNavbarController;
import com.presentation.mvc.views.login.LoginView;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;

//handles login 
public class LoginController {
    private LoginView view;
    private EmployeeModel model;
    public LoginController() {
        model = new EmployeeModel();
        // needs a eventhandler which is a funtional interface type
        //sets it with a method reference, these can be set with statics or with a instace reference
        view = new LoginView(model, this::login);
    }

    public void login(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.Login,
                model,
                this::loginAction
        ));
    }
    public LoginView getView() {
        return view;
    }
    public void loginAction(Object login) {
        Platform.runLater(
            () -> {
                if (login == null) {
                        Alerter.warning("Forkert", "Bruger kan ikke findes");
                    return;
                }
                    Employee employee = (Employee) login;

                    model.emailProperty().unbind();
                    model.passwordProperty().unbind();
                Facade.getInstance().login(employee);
                Facade.getInstance().setCenter(new HBox());
                Facade.getInstance().setLeft(new LeftNavbarController().getView());
                
            }
        );
    }

}
