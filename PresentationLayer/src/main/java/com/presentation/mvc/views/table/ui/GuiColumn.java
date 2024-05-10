package com.presentation.mvc.views.table.ui;

import com.presentation.mvc.models.table.RowModel;

import javafx.scene.control.TableColumn;

public class GuiColumn extends TableColumn<RowModel, RowModel> {
    private String rowsText;

    public GuiColumn(String text) {
        setText(text);
    }

    public String getRowsText() {
        return rowsText;
    }
    public void setRowsText(String rowsText) {
        this.rowsText = rowsText;
    }

}
