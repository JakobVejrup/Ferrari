package com.presentation.mvc.controllers.table;

import com.presentation.mvc.controllers.table.commands.CellCommand;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.views.table.ui.GuiCell;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CellController implements EventHandler<ActionEvent> {
    //graphic object in the cell
    private GuiCell cell;
    private CellCommand command;
    //booleanproperty to link the column with the checkbox
    private BooleanProperty booleanProperty;
    public CellController(NodeFactory factory, CellCommand command) {
        this.command = command;
        cell = new GuiCell(factory.createNode(this));
    }

    //mouse click event, checks booleans for if they were checked
    @Override
    public void handle(ActionEvent event) {
        if(cell.getItem() == null || !getBoolean())
            return;
        command.invoke(cell.getItem());
    }

    public Boolean getBoolean() {
        if (booleanProperty == null)
            return true;
        boolean bool = booleanProperty.get();
        booleanProperty.set(false);
        return bool;
    }
    public GuiCell getCell() {
        return cell;
    }
    public void setBooleanProperty(BooleanProperty property) {
        booleanProperty = property;
    }
}
