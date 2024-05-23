package com.presentation;

import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.agreement.OpenAgreementController;
import com.presentation.mvc.controllers.employee.EmployeesController;
import com.presentation.mvc.controllers.login.LoginController;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.topbar.TopbarView;
import com.presentation.tools.facade.Facade;
import com.presentation.tools.facade.Login;
import com.presentation.tools.facade.ModalSetter;
import com.presentation.tools.ScreenWatcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        ScreenWatcher.getInstance().setStage(stage);
        BorderPane bp = new BorderPane();
        bp.getStyleClass().add("mainPane");

        Scene scene = new Scene(bp);

        scene.getStylesheets().add(App.class.getResource("stylesheet1.css").toExternalForm());
        stage.setTitle("Ferrari");
        stage.setScene(scene);
        stage.show();
        Facade.getInstance().setMainPane(bp);
        Facade.getInstance().setModal(new ModalSetter(stage));
        Facade.getInstance().setLogin(new Login());
        Facade.getInstance().setTop(new TopbarView());
        OpenAgreementsModel model = new OpenAgreementsModel();
        model.setCustomer(new Customer());
        model.setEmployee(new Employee());
        model.setVehicle(new Vehicle());
        //Facade.getInstance().setCenter(new OpenAgreementController(model).getView());

        Facade.getInstance().setCenter(new LoginController().getView());
        //Facade.getInstance().openModal(new Request(ServiceType.Employee, CRUDType.Create));
        //Employee employee = new Employee();
        //employee.setOccupation(Occupation.Manager);
        //Facade.getInstance().login(employee);
        //Facade.getInstance().setCenter(new EmployeesController().getView());
    }

    public static void main(String[] args) {
        launch();
    }
}
