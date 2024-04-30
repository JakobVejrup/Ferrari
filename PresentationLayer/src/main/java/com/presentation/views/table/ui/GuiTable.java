package com.presentation.views.table.ui;

import com.presentation.views.table.GuiItem;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import java.util.ArrayList;
import java.util.List;

public abstract class GuiTable extends TableView<GuiItem> {
    private List<ButtonPlacement> buttons = new ArrayList<>();
    //goes through the columns and adds buttons or regions depending on param, the rgions and buttons will listen to the width and spread put above the table
    public void setup(Pane pane) {
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        GridPane grid = new GridPane();
        int buttonCounter = 0;
        Region region;
        for(int i = 0; i < getColumns().size(); i++) {
            if(buttonCounter < buttons.size() && buttons.get(buttonCounter).getPlacement() == i)
                region = buttons.get(buttonCounter++);
            else
                region = new Region();
            addListener(getColumns().get(i).widthProperty(), region);
            grid.add(region, i, 0);
        }
        pane.getChildren().addAll(grid, this);
    }
    //buttons extends Region deep in it's inheritance swamp, sets the button and region width to match the columns
    private void addListener(ReadOnlyDoubleProperty property, Region object) {
        property.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                object.setPrefWidth((double)newValue);
            }
        });
    }
    public void addButton(ButtonPlacement button) {
        buttons.add(button);
    }
}