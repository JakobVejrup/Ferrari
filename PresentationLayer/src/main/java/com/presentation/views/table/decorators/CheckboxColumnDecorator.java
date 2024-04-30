package com.presentation.views.table.decorators;


import com.presentation.controllers.table.commands.CellCommand;
import com.presentation.views.table.factory.CheckboxFactory;
import com.presentation.views.table.ui.ButtonPlacement;
import com.presentation.views.table.ui.GuiColumn;
import com.presentation.views.table.ui.GuiTable;

public class CheckboxColumnDecorator implements TableDecorator {
    private GuiTable table;
    public CheckboxColumnDecorator(CellCommand command, String columnText, String buttonText, TableDecorator other) {
        table = other.getTable();
        int tableSize = getTable().getColumns().size();
        ButtonPlacement button = new ButtonPlacement(buttonText, tableSize);
        table.addButton(button);
        table.getColumns().add(new GuiColumn(new CheckboxFactory(), columnText, command, button, tableSize));
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}
