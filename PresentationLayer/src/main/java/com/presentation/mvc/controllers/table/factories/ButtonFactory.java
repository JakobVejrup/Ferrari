package com.presentation.mvc.controllers.table.factories;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.views.table.ui.CellButton;
import javafx.scene.Node;
import javafx.scene.control.Button;
//anders
//makes the buttons and sets its css
public class ButtonFactory extends NodeFactory {
    private String css;
    public ButtonFactory(String css) {
        this.css = css;
    }
    @Override
    public Node createNode(CellController cell) {
        Button button = new CellButton(cell, getController().getRowText());
        button.getStyleClass().add(css);
        return button;
    }
}
