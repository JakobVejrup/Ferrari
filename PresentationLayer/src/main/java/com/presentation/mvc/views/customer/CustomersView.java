package com.presentation.mvc.views.customer;

import com.presentation.mvc.views.generalgui.NiceHBox;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CustomersView extends VBox {
    public CustomersView() {
        getStyleClass().add("Kunder");
    }
    public CustomersView(Button newUser) {
        getStyleClass().add("Kunder");
        getChildren().add(new NiceHBox("leftContainer", newUser));
    }
    public void setTable(GuiTable table) {
        table.setup(this);
    }
    
}
