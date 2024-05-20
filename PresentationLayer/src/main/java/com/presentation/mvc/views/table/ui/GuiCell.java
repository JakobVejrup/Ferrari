package com.presentation.mvc.views.table.ui;

import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.models.table.RowModel;

import javafx.scene.Node;
import javafx.scene.control.TableCell;

//DONT USE DEAD
public class GuiCell extends TableCell<RowModel, RowModel> {
    //graphic object in the cell
    private final Node node;

    public GuiCell(NodeFactory factory, CellController controller) {
        getStyleClass().add("guiCell");
        node = factory.createNode(controller);

    }
    //updateItem gets called whenever the observablelist changes and the rows get clicked
    @Override
    public void updateItem(RowModel item, boolean empty) {
        super.updateItem(item, empty);
        if (empty)
            setGraphic(null);
        else
            setGraphic(node);
    };
}
