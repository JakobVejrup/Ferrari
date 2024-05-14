package com.presentation.mvc.views.topbar;

import com.presentation.App;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TopbarView extends HBox {
    public TopbarView() {
        Image png = new Image(App.class.getResource("FerrariMiniLogo.png").toString());
        ImageView image = new ImageView(png);

        Double h = png.getHeight();
        Double w = png.getWidth();
        image.setFitHeight(h * 0.5);
        image.setFitWidth(w * 0.5);
        getChildren().add(image);
        getStyleClass().add("topbar");

    }
}
