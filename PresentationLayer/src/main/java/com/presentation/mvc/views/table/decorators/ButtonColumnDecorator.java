package com.presentation.mvc.views.table.decorators;


import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.views.table.ui.GuiTable;

//adds buttons to column cells, they dont need anything
public class ButtonColumnDecorator implements TableDecorator {
    private GuiTable table;
    public ButtonColumnDecorator(ColumnController column, TableDecorator other) {
        table = other.getTable();
        table.getColumns().add(column.getView());
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}
