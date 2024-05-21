package com.presentation.mvc.views.vehicle.modals;


import com.presentation.mvc.models.vehicle.VehicleModel;

import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

public class PriceVehicleView extends VehicleBaseView {
    public PriceVehicleView(VehicleModel model) {
        super(model);
        TextField price = new TextField(model.getPrice().toString());
        Bindings.bindBidirectional(price.textProperty(), model.priceProperty(), new NumberStringConverter());

        getChildren().addFirst(new HBox(new Label("Price:"), price));
    }
}
