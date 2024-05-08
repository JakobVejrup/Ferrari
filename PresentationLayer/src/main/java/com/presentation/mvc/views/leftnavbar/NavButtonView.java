package com.presentation.mvc.views.leftnavbar;

import com.presentation.mvc.models.leftnavbar.NavButtonModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.controlsfx.glyphfont.Glyph;

public class NavButtonView extends HBox {

    public NavButtonView(NavButtonModel model, EventHandler<MouseEvent> buttonAction) {
        super(new Label(model.getLabel()), new Glyph(model.getFontFamily(), model.getIcon()));
        getStyleClass().add("navButton");
        setOnMousePressed(buttonAction);
    }
}
