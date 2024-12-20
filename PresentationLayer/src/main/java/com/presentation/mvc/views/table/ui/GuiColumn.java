package com.presentation.mvc.views.table.ui;

import com.presentation.mvc.models.table.RowModel;
import javafx.scene.control.TableColumn;

//column for GUI thats why rowmodel in the column value, so it can get everything
public class GuiColumn extends TableColumn<RowModel, RowModel> {
    //text for cells
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
