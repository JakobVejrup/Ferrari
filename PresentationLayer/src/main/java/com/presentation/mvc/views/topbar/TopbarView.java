package com.presentation.mvc.views.topbar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TopView extends HBox {
    public TopView() {
        super(new ImageView(new Image("FerrariMiniLogo.png")));
    }
}
