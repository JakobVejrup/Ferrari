package com.presentation.mvc.views.table.ui;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CellImage extends ImageView{
        public CellImage(ObjectProperty<Image> property) {
        getStyleClass().add("cellImage");
        imageProperty().bind(property);
    }
}
