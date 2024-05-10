package com.presentation.mvc.models.table;

import com.logic.services.enums.ServiceType;
import javafx.collections.ObservableList;

import java.util.List;

public class TableModel {
    private ObservableList<RowModel> rows;
    private ServiceType type;
    public TableModel(ServiceType type, List<Object> objects) {
        this.type = type;
        this.rows = RowModel.makeRowModels(type, objects);
    }

    public ServiceType getType() {
        return type;
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
