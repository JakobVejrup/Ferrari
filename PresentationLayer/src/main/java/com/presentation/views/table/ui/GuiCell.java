package com.presentation.views.table.ui;

import com.presentation.controllers.table.commands.CellCommand;
import com.presentation.controllers.table.factory.NodeFactory;
import com.presentation.views.table.GuiItem;

import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableCell;


public class GuiCell extends TableCell<GuiItem, GuiItem> implements EventHandler<ActionEvent> {
    //graphic object in the cell
    private Node node;
    private CellCommand command;
    //booleanproperty to link the column with the checkbox
    private BooleanProperty booleanProperty;
    public GuiCell(NodeFactory factory, GuiColumn column, CellCommand command) {
        getStyleClass().add("guiCell");
        this.command = command;
        node = factory.createNode(this, column);

    }
    //updateItem gets called whenever the observablelist changes and the rows get clicked
    @Override
    public void updateItem(GuiItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty)
            setGraphic(null);
        else
            setGraphic(node);
    };
    //mouse click event, checks booleans for if they were checked
    @Override
    public void handle(ActionEvent event) {
        if(getItem() == null || !getBoolean())
            return;
        command.invoke(getItem(), this);
    }
    //used from outside to remove this rows item
    public void emptyRow() {
        getTableView().getItems().remove(getItem());
    }
    public Boolean getBoolean() {
        if (booleanProperty == null)
            return true;
        boolean bool = booleanProperty.get();
        booleanProperty.set(false);
        return bool;
    }
    public void setBooleanProperty(BooleanProperty property) {
        booleanProperty = property;
    }
}
