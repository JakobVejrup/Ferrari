package com.presentation.views.table.ui;

import javafx.scene.control.Button;
//button in the cells
public class CellButton extends Button {
    public CellButton(GuiCell cell, String text) {
        super(text);
        setOnAction(cell);
    }
}
