package com.presentation.mvc.views.vehicle;

import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class VehiclesView extends VBox {
// Konstruktør til at lave VehiclesView med knapper
    public VehiclesView(Button... buttons) {
// Tilføjer og opretter styling til Vbox og NiceHBox
        getStyleClass().add("vehicles");
        getChildren().addAll(new NiceHBox("leftContainer", buttons));
    }

}

