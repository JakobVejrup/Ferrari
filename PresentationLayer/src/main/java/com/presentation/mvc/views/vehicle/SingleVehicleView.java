package com.presentation.mvc.views.vehicle;

import java.io.ByteArrayInputStream;
import com.model.entities.Vehicle;
import com.presentation.mvc.views.View;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//jakob
public class SingleVehicleView extends VBox implements View{
    public Vehicle model;
    public TextField price;
    public TextField name;
    public ImageView image;

    public SingleVehicleView(Vehicle model) {
        getStyleClass().add("rightContainer");
        this.model = model;
        
        price = new TextField(String.valueOf(model.getPrice()));
        price.setEditable(false);
        
        name = new TextField(model.getName());
        name.setEditable(false);
        
        image = new ImageView();
        if(model.getImage() != null) 
        image.setImage(new Image(new ByteArrayInputStream(model.getImage()), 0, 200, true, true));
        getChildren().addAll(
            new NiceHBox("rightContainer", new Insets(5), new Label("Bil navn: "), name ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Bil pris: "), price ),
            new NiceHBox("rightContainer", new Insets(5), new Label("Bil billede: "), image )
        );
    }

    public void setModel(Vehicle model) {
        this.model = model;
        name.setText(model.getName());
        price.setText(String.valueOf(model.getPrice()));
        if(model.getImage() != null)
            image.setImage(new Image(new ByteArrayInputStream(model.getImage()), 0, 200, true, true));
    }

    @Override
    public void addButtons(Button... buttons) {
        getChildren().addFirst(new HBox(buttons));
    }
}
