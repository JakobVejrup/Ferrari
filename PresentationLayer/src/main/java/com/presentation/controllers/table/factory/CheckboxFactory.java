package com.presentation.controllers.table.factory;

import com.presentation.views.table.GuiItem;
import com.presentation.views.table.ui.CellCheckBox;
import com.presentation.views.table.ui.GuiCell;
import com.presentation.views.table.ui.GuiColumn;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class CheckboxFactory implements NodeFactory{
    @Override
    public Node createNode(GuiCell cell, GuiColumn column) {
        //makes a property which column will listen to, so it can check if button should be disabled
        BooleanProperty booleanProperty = new SimpleBooleanProperty();
        booleanProperty.addListener(column);
        //adds a listener to when the items in the row change, because if several checkboxes exist they have to be reset upon one call
        cell.itemProperty().addListener(new ChangeListener<GuiItem>() {
            @Override
            public void changed(ObservableValue<? extends GuiItem> observable, GuiItem oldValue, GuiItem newValue) {
                if(oldValue != null)
                    booleanProperty.unbindBidirectional(oldValue.getProperty(column.getNr()));
                if(newValue != null) {
                    booleanProperty.set(newValue.getProperty(column.getNr()).get());
                    booleanProperty.bindBidirectional(newValue.getProperty(column.getNr()));
                }
            }
        });
        //Adds cells to the column button
        column.getButton().addEventHandler(ActionEvent.ACTION, cell);
        cell.setBooleanProperty(booleanProperty);
        return new CellCheckBox(column.getRowsText(), booleanProperty);
    }
}
