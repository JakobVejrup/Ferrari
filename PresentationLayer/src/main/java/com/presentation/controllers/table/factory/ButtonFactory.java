package com.presentation.controllers.table.factory;

import com.presentation.views.table.ui.CellButton;
import com.presentation.views.table.ui.GuiCell;
import com.presentation.views.table.ui.GuiColumn;
import javafx.scene.Node;

public class ButtonFactory implements NodeFactory{
    @Override
    public Node createNode(GuiCell cell, GuiColumn column) {
        return new CellButton(cell, column.getRowsText());
    }
}
