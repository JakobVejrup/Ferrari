package com.presentation.mvc.controllers.employee.modals;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.threads.Validation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.employee.EmployeeImageView;
import com.presentation.mvc.views.employee.modals.PasswordEmployeeView;
import com.presentation.tools.ImageFinder;
import com.presentation.tools.alert.Alerter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class CreateEmployeeController extends ModalController {
    private EmployeeModel model;
    private HBox view;
    public CreateEmployeeController(Stage stage) {
        super(stage);

        model = new EmployeeModel();
        // needs a actionevent which is a funtional interface type
        Button createButton = new Button("Lav Bruger");
        createButton.setOnAction(this::create);

        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        Button imageButton = new Button("Vælg Billede");
        imageButton.setOnAction(this::findImage);

        PasswordEmployeeView viewRight = new PasswordEmployeeView(model);
        viewRight.addButtons(createButton, cancelButton);
        
        EmployeeImageView viewLeft = new EmployeeImageView(model);
        viewLeft.addButtons(imageButton);
        view = new HBox(viewLeft, viewRight);
    }
    public void findImage(ActionEvent event) {
        byte[] image = ImageFinder.findImage((Stage)view.getScene().getWindow());
        if (image != null) 
            model.setImage(image);
    }
    @Override
    public Pane getView() {
        return view;
    }
    public void create(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Employee, CRUDType.Create,
                model,
                (newEmployee) -> {
                    if(newEmployee != null) {
                        setResult(newEmployee);
                        Platform.runLater(this::close);
                    }
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
    public void decline(ActionEvent event) {
        close();
    }

}
