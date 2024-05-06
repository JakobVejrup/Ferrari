package com.presentation.mvc.views.table.decorators;


import com.presentation.mvc.views.table.ui.GuiColumn;
import com.presentation.mvc.views.table.ui.GuiTable;

public class ButtonColumnDecorator implements TableDecorator {
    private GuiTable table;
    public ButtonColumnDecorator(GuiColumn column, TableDecorator other) {
        table = other.getTable();
        table.getColumns().add(column);
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}
