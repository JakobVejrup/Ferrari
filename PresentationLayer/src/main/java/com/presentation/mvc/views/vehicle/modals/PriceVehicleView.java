package com.presentation.mvc.views.vehicle.modals;

import com.presentation.mvc.models.vehicle.VehicleModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class PriceVehicleView {
    public PriceVehicleView(VehicleModel model) {
        super();
        PriceField price = new PriceField();
        model.pricProperty().bind(price.textProperty());
        getChildren().addFirst(new HBox(new Label("Price:"). price));
    }
}
