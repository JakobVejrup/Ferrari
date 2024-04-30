package com.presentation.views.table.decorators;

import com.presentation.views.table.GuiItem;
import com.presentation.views.table.ui.GuiTable;
import javafx.collections.ObservableList;

public class ParentTableDecorator implements TableDecorator {
    private GuiTable table;
    public ParentTableDecorator(ObservableList<GuiItem> objects, TableDecorator other) {
        table = other.getTable();
        table.getStyleClass().add("bigTable");
        table.setItems(objects);
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