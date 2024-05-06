package com.presentation;

import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.login.LoginController;
import com.presentation.tools.facade.Facade;
import com.presentation.tools.facade.Login;
import com.presentation.tools.facade.ModalFactory;
import com.presentation.tools.facade.ViewFactory;
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
        Scene scene = new Scene(bp);
        //in a lambda/dynamic method, to use a variable in of polymorphic nature you must ensure that its "final" and wont change class, to do this you can ensure its set once or you can make another "final" reference

        //TableDecorator table = new ParentTableDecorator(items, new TableWidthListenerDecorator(0.5, new TableHeightDecorator(0.8, new ExampleGuiTable())));
        //table = new TableInCellDecorator(new TestTableFactory(),"Tests", new CheckboxColumnDecorator(new DeleteCommand(), "Slet", "Slet", table));
        //table = new ButtonColumnDecorator(new UpdateCommand(),"Opdater", "Opdater", table);
        //TableDecorator finalTable = table;

        // if you dont the instantiation can get rather long as below
        //TableDecorator table = new ButtonColumnDecorator(new UpdateCommand(),"Opdater", "Opdater", new TableInCellDecorator(new TestTableFactory(),"Tests", new CheckboxColumnDecorator(new DeleteCommand(), "Slet", "Slet", new ParentTableDecorator(new TableWidthListenerDecorator(0.5, new TableHeightDecorator(0.8, new ExampleGuiTable()))))));
        //table.getTable().setup(box);

        //ServiceSingleton.getInstance().query(new Request(ServiceType.Example, CRUDType.ReadAll,
        //        //parameter is the value from the call to the data layer
        //        (parameter) -> table.getTable().setItems(GuiItem.getGuiItems((List<Object>)parameter, ServiceType.Example))
        //));

        scene.getStylesheets().add(App.class.getResource("stylesheet1.css").toExternalForm());
        stage.setTitle("Ferrari");
        stage.setScene(scene);
        stage.show();
        Facade.getInstance().setMainPane(bp);
        Facade.getInstance().setModal(new ModalFactory(stage));
        Facade.getInstance().setLogin(new Login());
        Facade.getInstance().setCenter(new LoginController().getView());
        //Facade.getInstance().openModal(new Request(ServiceType.Employee, CRUDType.Create));



    }

    public static void main(String[] args) {
        launch();
    }
}
