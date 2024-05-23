package com.presentation.mvc.views.vehicle;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;



public class VehiclesView extends VBox {
    public VehiclesView(Button... buttons) {
        getStyleClass().add("vehicles");
        getChildren().addAll(buttons);
    }

}

