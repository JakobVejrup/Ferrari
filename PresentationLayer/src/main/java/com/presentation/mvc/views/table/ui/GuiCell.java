package com.presentation.mvc.views.table.ui;

import com.presentation.mvc.models.table.RowModel;

import javafx.scene.Node;
import javafx.scene.control.TableCell;


public class GuiCell extends TableCell<RowModel, RowModel> {
    //graphic object in the cell
    private final Node node;

    public GuiCell(Node node) {
        getStyleClass().add("guiCell");
        this.node = node;

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
