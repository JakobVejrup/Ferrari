package com.presentation.mvc.controllers.table.factories;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.ColumnController;

import javafx.scene.Node;

public abstract class NodeFactory {
     private ColumnController controller;

     public void setController(ColumnController controller) {
          this.controller = controller;
     }

     public ColumnController getController() {
          return controller;
     }
     public abstract Node createNode(CellController cell);

}
