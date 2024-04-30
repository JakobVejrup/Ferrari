package com.presentation.views.table.ui;

import com.presentation.controllers.table.commands.CellCommand;
import com.presentation.views.table.GuiItem;
import com.presentation.views.table.factory.NodeFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class GuiColumn extends TableColumn<GuiItem, GuiItem> implements Callback<TableColumn<GuiItem, GuiItem>, TableCell<GuiItem, GuiItem>>, ChangeListener<Boolean> {
    private int selected;
    private Button button;
    private String rowsText;
    private NodeFactory factory;
    private CellCommand command;
    private int nr;
    //constructor sets cellfactory on this class
    public GuiColumn(NodeFactory factory, String text) {
        this.factory = factory;
        setText(text);
        setCellFactory(this);
        //can only implement one callback, so this is a dynamic object to keep it in the same class
        setCellValueFactory(new Callback<CellDataFeatures<GuiItem, GuiItem>, ObservableValue<GuiItem>>() {
            @Override
            public ObservableValue<GuiItem> call(CellDataFeatures<GuiItem, GuiItem> column) {
                return new SimpleObjectProperty<GuiItem>(column.getValue());
            }
        });
    }
    //Button version constructor
    public GuiColumn(NodeFactory factory, String text, CellCommand command, String rowsText) {
        this(factory, text);
        this.command = command;
        this.rowsText = rowsText;
    }
    //checkbox version constructor
    public GuiColumn(NodeFactory factory, String text, CellCommand command, Button button, int nr) {
        this(factory, text);
        rowsText = text;
        this.command = command;
        this.button = button;
        this.nr = nr;
    }
    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
        selected += newValue ? 1 : -1;
        button.setDisable(!(selected > 0));
    }
    @Override
    public TableCell<GuiItem, GuiItem> call(TableColumn<GuiItem, GuiItem> param) {
        return new GuiCell(factory, this, command);
    }
    //set button from fxml listeners can be added
    public void setButton(Button value) {
        button = value;
    }
    public Button getButton() {
        return button;
    }

    public int getNr() {
        return nr;
    }

    //get/set to allow setting of properties in fxml
    public String getRowsText() {
        return rowsText;
    }
    public void setRowsText(String rowsText) {
        this.rowsText = rowsText;
    }
    public NodeFactory getFactory() {
        return factory;
    }
    public void setFactory(NodeFactory factory) {
        this.factory = factory;
    }

}
