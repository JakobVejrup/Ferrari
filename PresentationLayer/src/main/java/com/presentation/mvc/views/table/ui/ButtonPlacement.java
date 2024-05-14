package com.presentation.mvc.views.table.ui;

import javafx.scene.control.Button;

public class ButtonPlacement extends Button {
    private int placement;
    // button for columns, set disabled as default
    public ButtonPlacement(String text, int placement) {
        super(text);
        this.placement = placement;
        setDisable(true);
    }
    public int getPlacement() {
        return placement;
    }
    public void setPlacement(int placement) {
        this.placement = placement;
    }
}
