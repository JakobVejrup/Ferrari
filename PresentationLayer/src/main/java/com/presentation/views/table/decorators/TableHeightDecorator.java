package com.presentation.views.table.decorators;

import com.presentation.tools.ScreenWatcher;
import com.presentation.views.table.ui.GuiTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Screen;

public class TableHeightDecorator implements TableDecorator {
    private GuiTable table;
    public TableHeightDecorator(double decimal, TableDecorator other) {
        this.table = other.getTable();
        this.table.setPrefHeight(ScreenWatcher.getInstance().getScreenHeightWithDecimal(decimal));
        ScreenWatcher.getInstance().getScreenProperty().addListener(new ChangeListener<Screen>() {
            @Override
            public void changed(ObservableValue<? extends Screen> observable, Screen oldValue, Screen newValue) {
                table.setPrefHeight(newValue.getBounds().getHeight() * decimal);
            }
        });
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}
