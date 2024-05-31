package com.presentation.mvc.controllers.table.factories;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.ColumnController;
import javafx.scene.Node;
//anders
// abstract class to make gui
public abstract class NodeFactory {
     private ColumnController controller;

     public void setController(ColumnController controller) {
          this.controller = controller;
     }

     public ColumnController getController() {
          return controller;
     }
     //creates gui as implemented
     public abstract Node createNode(CellController cell);

}
