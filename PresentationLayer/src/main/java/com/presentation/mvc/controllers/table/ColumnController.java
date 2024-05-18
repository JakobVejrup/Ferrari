package com.presentation.mvc.controllers.table;

import com.presentation.mvc.controllers.table.commands.CellCommand;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.ui.GuiColumn;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ColumnController implements Callback<TableColumn<RowModel, RowModel>, TableCell<RowModel, RowModel>>, ChangeListener<Boolean> {
    private int selected;
    private Button button;
    private NodeFactory nodeFactory;
    private CellCommand command;
    private final GuiColumn view;
    private String rowText;
    private int nr;
    private ColumnController(String text, NodeFactory nodeFactory, String rowsText) {
        this.nodeFactory = nodeFactory;
        nodeFactory.setController(this);
        view = new GuiColumn(text);
        this.rowText = rowsText;
        view.setCellFactory(this);
        //can only implement one callback, so this is a dynamic object to keep it in the same class
        view.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, RowModel>, ObservableValue<RowModel>>() {
            @Override
            public ObservableValue<RowModel> call(TableColumn.CellDataFeatures<RowModel, RowModel> column) {
                return new SimpleObjectProperty<RowModel>(column.getValue());
            }
        });
    }
    //Image version constructor
    public ColumnController(NodeFactory nodeFactory, String text, int nr) {
        this(text, nodeFactory, "");
        this.nr = nr;
    }
    //Button version constructor
    public ColumnController(NodeFactory nodeFactory, String text, CellCommand command, String rowsText) {
        this(text, nodeFactory, rowsText);
        this.command = command;
    }
    //checkbox version constructor
    public ColumnController(NodeFactory nodeFactory, String rowsText, String text, CellCommand command, Button button, int nr) {
        this(text, nodeFactory, rowsText);
        this.command = command;
        this.button = button;
        this.nr = nr;
    }
    @Override
    public TableCell<RowModel, RowModel> call(TableColumn<RowModel, RowModel> param) {
        return new CellController(nodeFactory, command).getCell();
    }
    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean newValue) {
        selected += newValue ? 1 : -1;
        button.setDisable(!(selected > 0));
    }
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public GuiColumn getView () {
        return view;
    }
    public int getNr() {
        return nr;
    }
    public NodeFactory getFactory() {
        return nodeFactory;
    }
    public void setFactory(NodeFactory nodeFactory) {
        this.nodeFactory = nodeFactory;
    }

    public String getRowText() {
        return rowText;
    }
}
