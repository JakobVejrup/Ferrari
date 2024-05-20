package com.presentation.mvc.views.vehicle.modals;

import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VehicleBaseView extends VBox implements View {
    public VehicleBaseView(VehicleModel model) {
        TextField name = new TextField(model.getName());
        model.nameProperty().bind(name.textProperty());

        TextField vehicleID = new TextField();
        model.vehicleIdProperty().bind(vehicleID.textProperty());

        TextField price = new TextField(String.valueOf(model.getPrice()));
        price.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                    if (!newValue.matches("//d*"));
                        price.setText(newValue.replaceFirst("[¨//d]", ""));
                    if (!price.getText().isEmpty())
                        model.pricProperty().set(Integer.parseInt(price.getText()));
                                }
        });

    getChildren().addAll(
        new HBox(new Label("Id:"), vehicleID),
        new HBox(new Label("Name:"), name),
        new HBox(new Label("Price:"), price)
    );
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
}
