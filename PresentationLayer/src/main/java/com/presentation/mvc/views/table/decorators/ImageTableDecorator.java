package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.factories.ImageFactory;
import com.presentation.mvc.views.table.ui.GuiTable;

public class ImageTableDecorator implements TableDecorator {
    private GuiTable table;
    public ImageTableDecorator(TableDecorator other) {
        table = other.getTable();
        int tableSize = getTable().getColumns().size();
        ColumnController controller = new ColumnController(new ImageFactory(), "Billeder", tableSize);
        table.getColumns().add(controller.getView());
    }

    @Override
    public GuiTable getTable() {
        return table;
    }
}
