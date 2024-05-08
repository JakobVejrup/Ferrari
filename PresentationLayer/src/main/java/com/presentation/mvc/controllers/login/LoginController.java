package com.presentation.mvc.controllers.login;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.presentation.mvc.controllers.leftnavbar.LeftNavbarController;
import com.presentation.mvc.views.login.LoginView;
import com.presentation.mvc.models.login.LoginModel;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;

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
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.Login,
                employee,
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
                Facade.getInstance().login(employee);
                Facade.getInstance().setCenter(new HBox());
                Facade.getInstance().setLeft(new LeftNavbarController().getView());
            }
        );
    }

}
