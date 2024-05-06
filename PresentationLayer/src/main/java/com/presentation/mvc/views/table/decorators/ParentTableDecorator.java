package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.table.ui.GuiTable;

public class ParentTableDecorator implements TableDecorator {
    private GuiTable table;
    public ParentTableDecorator(TableModel model, TableDecorator other) {
        table = other.getTable();
        table.getStyleClass().add("bigTable");
        table.setItems(model.getRows());
    }
    public ParentTableDecorator(TableDecorator other) {
        table = other.getTable();
        table.getStyleClass().add("bigTable");
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}