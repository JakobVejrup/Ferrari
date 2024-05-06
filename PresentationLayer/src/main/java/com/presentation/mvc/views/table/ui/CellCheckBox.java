package com.presentation.mvc.views.table.ui;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
//checkbox in cells
public class CellCheckBox extends CheckBox {
    public CellCheckBox(String text, BooleanProperty property) {
        super(text);
        getStyleClass().add("cellCheck");
        selectedProperty().bindBidirectional(property);
    }
}
