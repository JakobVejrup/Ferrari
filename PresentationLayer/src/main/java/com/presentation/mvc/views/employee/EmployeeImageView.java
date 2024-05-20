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
    public EmployeeImageView(EmployeeModel model) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);
        if(model.getImage() != null)
            imageView.setImage(new Image(new ByteArrayInputStream(model.getImage())));
        model.imageProperty().addListener(new ChangeListener<byte[]>() {
            @Override
            public void changed(ObservableValue<? extends byte[]> observable, byte[] oldValue, byte[] newValue) {
                Image img = new Image(new ByteArrayInputStream(newValue));
                imageView.setImage(img);
            }
        });
        getChildren().add(imageView);
    }
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
}
