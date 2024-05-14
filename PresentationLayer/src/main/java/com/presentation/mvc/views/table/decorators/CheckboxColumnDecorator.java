package com.presentation.mvc.views.table.decorators;


import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.CellCommand;
import com.presentation.mvc.controllers.table.factories.CheckboxFactory;
import com.presentation.mvc.views.table.ui.ButtonPlacement;
import com.presentation.mvc.views.table.ui.GuiTable;

public class CheckboxColumnDecorator implements TableDecorator {
    private GuiTable table;
    public CheckboxColumnDecorator(CellCommand command, String columnText, String rowText, String buttonText, TableDecorator other) {
        table = other.getTable();
        int tableSize = getTable().getColumns().size();
        ButtonPlacement button = new ButtonPlacement(buttonText, tableSize);
        table.addButton(button);
        ColumnController controller = new ColumnController(new CheckboxFactory(), rowText, columnText, command, button, tableSize);
        table.getColumns().add(controller.getView());
    }
    public CheckboxColumnDecorator(ColumnController controller, String buttonText, TableDecorator other) {
        table = other.getTable();
        int tableSize = getTable().getColumns().size();
        ButtonPlacement button = new ButtonPlacement(buttonText, tableSize);
        controller.setButton(button);
        table.addButton(button);
        table.getColumns().add(controller.getView());
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}
