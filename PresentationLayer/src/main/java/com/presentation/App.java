package com.presentation;

import com.presentation.tools.ScreenWatcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        ScreenWatcher.getInstance().setStage(stage);

        VBox box = new VBox();
        Scene scene = new Scene(box, 900, 900);
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
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        //ServiceSingleton.getInstance().query(new SQLRequest(ServiceType.Example, CRUDType.ReadAll));
        //service.query(new SQLRequest(ServiceType.Example, CRUDType.Delete, new Example(1, "")));
    }

    public static void main(String[] args) {
        launch();
    }
}
