package com.presentation.mvc.views.customer;

import com.presentation.mvc.views.table.concretes.CustomerTable;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CustomersView extends VBox {
    private Button newUser;
    public CustomersView(EventHandler<ActionEvent> newUserAction) {
        getStyleClass().add("Kunder");
        newUser = new Button("Lav ny kunde");
        newUser.setOnAction(newUserAction);
        getChildren().add(newUser);
    }
    public void setTable(GuiTable table) {
        table.setup(this);
    }
    
}
