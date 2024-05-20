package com.presentation.mvc.views.vehicle.modals;

import com.presentation.mvc.models.vehicle.VehicleModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class CreateVehicleView extends VehicleBaseView {
    public CreateVehicleView(VehicleModel model) {
        super(model);
        PriceField price = new PriceField();
        model.pricProperty().bind(price.textProperty());
        getChildren().addFirst(new HBox(new Label("Price:"). price));
    }
}
