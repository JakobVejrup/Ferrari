package com.presentation.mvc.controllers.table.factories;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.views.table.ui.CellButton;

import javafx.scene.Node;

public class ButtonFactory extends NodeFactory {

    @Override
    public Node createNode(CellController cell) {
        return new CellButton(cell, getController().getRowText());
    }
}
