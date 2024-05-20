package com.presentation.mvc.views.vehicle.modals;

import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.View;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class VehicleBaseView extends VBox implements View {
    public VehicleBaseView(VehicleModel model) {
        TextField name = new TextField(model.getName());
        model.nameProperty().bind(name.textProperty());

        TextField price = new TextField(model.getPrice().toString());
        Bindings.bindBidirectional(price.textProperty(), model.priceProperty(), new NumberStringConverter());

        getChildren().addAll(
        new HBox(new Label("Name:"), name),
        new HBox(new Label("Price:"), price)
    );
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
}
