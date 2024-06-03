package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.views.table.ui.GuiTable;
//anders
//sets columns view as gui and that is gonna be a child table, in each row with a item
public class ChildTableDecorator implements TableDecorator {
    private GuiTable table;
    public ChildTableDecorator(ColumnController column, TableDecorator other) {
        table = other.getTable();
        table.getColumns().add(column.getView());
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}