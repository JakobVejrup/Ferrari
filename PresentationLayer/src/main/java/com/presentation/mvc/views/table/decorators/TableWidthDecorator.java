package com.presentation.mvc.views.table.decorators;

import com.presentation.mvc.views.table.ui.GuiTable;
import com.presentation.tools.ScreenWatcher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Screen;

//sets table width to be a percent of the screenwidth
public class TableWidthDecorator implements TableDecorator {
    private GuiTable table;
    public TableWidthDecorator(double decimal, TableDecorator other) {
        this.table = other.getTable();
        this.table.setMaxWidth(ScreenWatcher.getInstance().getScreenWidthWithDecimal(decimal));
        ScreenWatcher.getInstance().getScreenProperty().addListener(new ChangeListener<Screen>() {
            @Override
            public void changed(ObservableValue<? extends Screen> observable, Screen oldValue, Screen newValue) {
                table.setPrefWidth(newValue.getBounds().getWidth() * decimal);
            }
        });
    }
    @Override
    public GuiTable getTable() {
        return table;
    }
}
