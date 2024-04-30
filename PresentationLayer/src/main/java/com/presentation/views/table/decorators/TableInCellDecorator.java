package com.presentation.views.table.decorators;

import com.presentation.views.table.factory.NodeFactory;
import com.presentation.views.table.ui.GuiColumn;
import com.presentation.views.table.ui.GuiTable;

public class TableInCellDecorator implements TableDecorator {
    private GuiTable table;
    public TableInCellDecorator(NodeFactory tableFactory, String columnText, TableDecorator other) {
        table = other.getTable();
        table.getColumns().add(new GuiColumn(tableFactory, columnText));
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}