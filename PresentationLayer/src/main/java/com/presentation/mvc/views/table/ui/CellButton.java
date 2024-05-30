package com.presentation.mvc.views.table.ui;

import com.presentation.mvc.controllers.table.CellController;
import javafx.scene.control.Button;
//button in the cells
public class CellButton extends Button {
    public CellButton(CellController cell, String text) {
        super(text);
        getStyleClass().add("cellButton");
        setOnAction(cell);
    }
}
