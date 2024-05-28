package com.presentation.mvc.controllers.table;

import java.text.DecimalFormat;

import com.presentation.mvc.models.table.RowModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

public class CurrencyCell extends TableCell<RowModel, Number> {
    private final DecimalFormat format = new DecimalFormat("#.00");

    @Override
    protected void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(format.format(item) + ".Kr");
        }
    }

    public static Callback<TableColumn<RowModel, Number>, TableCell<RowModel, Number>> forTableColumn() {
        return (column) -> new CurrencyCell();
    }
}
