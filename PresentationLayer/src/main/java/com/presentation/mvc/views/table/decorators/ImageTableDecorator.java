package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.factories.ImageFactory;
import com.presentation.mvc.views.table.ui.GuiTable;

public class ImageTableDecorator implements TableDecorator {
    private GuiTable table;
    public ImageTableDecorator(TableDecorator other, ImageFactory factory) {
        table = other.getTable();
        ColumnController controller = new ColumnController(factory, "Billede");

        table.getColumns().add(controller.getView());
    }

    @Override
    public GuiTable getTable() {
        return table;
    }
}
