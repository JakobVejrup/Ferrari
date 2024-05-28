package com.presentation.mvc.controllers.employee;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.employee.EmployeeImageView;
import com.presentation.mvc.views.employee.modals.PasswordEmployeeView;
import com.presentation.tools.FileMethods;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AccountController extends Controller{
    private HBox view;
    private EmployeeModel model;

    public AccountController() {
        model = new EmployeeModel(Facade.getInstance().getLoggedIn());
        Button updateButton = new Button("Opdater dig");
        updateButton.setOnAction(this::updateSelf);
        Button imageButton = new Button("Vælg Billede");
        imageButton.setOnAction(this::findImage);
        
        PasswordEmployeeView viewRight = new PasswordEmployeeView(model);
        viewRight.addButtons(updateButton);
        
        EmployeeImageView viewLeft = new EmployeeImageView(model);
        viewLeft.addButtons(imageButton);
        view = new HBox(viewLeft, viewRight);
        setView(view);
    }


    public void findImage(ActionEvent event) {
        byte[] image = FileMethods.findImage((Stage)view.getScene().getWindow());
        if (image != null) 
            model.setImage(image);
    }
    public void updateSelf(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.UpdateSelf,
                model,
                (update) -> {
                    if(update != null) 
                        Facade.getInstance().getLoggedIn().copy((Employee)update);
                },
                new Validation(
                    (request) -> {
                        Validation validation = ((Request) request).getValidation();
                        Platform.runLater(
                            () -> Alerter.information("Forkerte data", validation.getMessages())
                        );
                    }
                )
            )
        );
    }

}
