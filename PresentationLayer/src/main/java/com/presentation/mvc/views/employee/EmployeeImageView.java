package com.presentation.mvc.views.employee;

import java.io.ByteArrayInputStream;

import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.views.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EmployeeImageView extends VBox implements View{
    private ImageView image;
    public EmployeeImageView(EmployeeModel model) {
        ImageView image = new ImageView();
        image.setFitHeight(300);
        image.setPreserveRatio(true);
        if(model.getImage() != null)
        image.setImage(new Image(new ByteArrayInputStream(model.getImage()), 0, 300, true, true));
        model.imageProperty().addListener(new ChangeListener<byte[]>() {
            @Override
            public void changed(ObservableValue<? extends byte[]> observable, byte[] oldValue, byte[] newValue) {
                Image img = new Image(new ByteArrayInputStream(newValue), 0, 300, true, true);
                image.setImage(img);
            }
        });
        getChildren().add(image);
    }
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
}
