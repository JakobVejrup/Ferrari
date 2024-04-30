package com.presentation.controllers.table.factory;

import com.presentation.views.table.ui.GuiCell;
import com.presentation.views.table.ui.GuiColumn;
import javafx.scene.Node;

public interface NodeFactory {
     public Node createNode(GuiCell cell, GuiColumn column);
}
