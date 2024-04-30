package com.presentation.views.table.decorators;


import com.presentation.controllers.table.commands.CellCommand;
import com.presentation.controllers.table.factory.ButtonFactory;
import com.presentation.views.table.ui.GuiColumn;
import com.presentation.views.table.ui.GuiTable;

public class ButtonColumnDecorator implements TableDecorator {
    private GuiTable table;
    public ButtonColumnDecorator(CellCommand command, String columnText, String buttonText, TableDecorator other) {
        table = other.getTable();
        table.getColumns().add(new GuiColumn(new ButtonFactory(), columnText, command, buttonText));
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}
