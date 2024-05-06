package com.presentation.mvc.models.table;

import com.logic.services.enums.ServiceType;
import javafx.collections.ObservableList;

public class TableModel {
    private ObservableList<RowModel> rows;
    public TableModel(ServiceType type, Object... objects) {
        this.rows = RowModel.makeRowModels(type, (Object[])objects);
    }
    public void removeRow(RowModel row) {
        rows.remove(row);
    }
    public void addRow(RowModel row) {
        rows.add(row);
    }
    public ObservableList<RowModel> getRows() {
        return rows;
    }
}
