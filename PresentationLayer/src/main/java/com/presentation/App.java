package com.presentation;

import java.util.List;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.presentation.mvc.controllers.employee.modals.CreateEmployeeController;
import com.presentation.mvc.controllers.login.LoginController;
import com.presentation.mvc.views.topbar.TopbarView;
import com.presentation.tools.ScreenWatcher;
import com.presentation.tools.facade.Facade;
import com.presentation.tools.facade.Login;
import com.presentation.tools.facade.ModalSetter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//alle
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
        Facade.getInstance().setCenter(new LoginController().getView());
        Request request = new Request(ServiceType.Employee, CRUDType.ReadAll, (employees) -> {
            if(((List<Employee>)employees).size() == 0)
            Platform.runLater( () -> Facade.getInstance().openModal(new CreateEmployeeController()));
        });

    }

    public static void main(String[] args) {
        launch();
    }
}
