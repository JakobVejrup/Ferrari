package com.presentation.mvc.views.vehicle;

import java.io.ByteArrayInputStream;

import com.model.entities.Customer;
import com.model.entities.Vehicle;
import com.presentation.mvc.views.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SingleVehicleView extends VBox implements View{
    public Vehicle model;
    public TextField price;
    public TextField name;
    public ImageView image;

    public SingleVehicleView(Vehicle model) {
        this.model = model;

        price = new TextField(model.getPrice().toString());
        price.setEditable(false);

        name = new TextField(model.getVehicleName());
        name.setEditable(false);

        image = new ImageView(new Image(new ByteArrayInputStream(model.getImage())));

        getChildren().addAll(
            new HBox(new Label("Bil navn: "), name ),
            new HBox(new Label("Bil pris: "), price ),
            new HBox(new Label("Bil billede: "), image )
        );
        setAlignment(Pos.CENTER);
    }

    public void setModel(Vehicle model) {
        this.model = model;
        name.setText(model.getVehicleName());
        price.setText(model.getPrice().toString());
        image.setImage(new Image(new ByteArrayInputStream(model.getImage())));
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().addFirst(new HBox(buttons));
    }
}
