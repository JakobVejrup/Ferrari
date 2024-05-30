package com.presentation.mvc.models.table;

import com.logic.services.enums.ServiceType;
import com.model.threads.ActionParameter;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;
//anders
public class TableModel {
    private ObservableList<RowModel> rows;
    private ServiceType type;
    public TableModel(ServiceType type, List<Object> objects) {
        this.type = type;
        this.rows = RowModel.makeRowModels(type, objects);
    }
    public TableModel(ServiceType type, Object[] objects) {
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
    public void removeAllRows() {
        rows.removeAll(rows);
    }
    public void addAllRows(List<RowModel> addRows) {
        rows.addAll(addRows);
    }
    public ObservableList<RowModel> getRows() {
        return rows;
    }
}
