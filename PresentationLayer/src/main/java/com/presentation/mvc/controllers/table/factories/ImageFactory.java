package com.presentation.mvc.controllers.table.factories;

import com.model.entities.Employee;
import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.ui.CellImage;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;

public class ImageFactory extends NodeFactory{

    @Override
    public Node createNode(CellController cell) {
        ObjectProperty<byte[]> imageProperty = new SimpleObjectProperty<>();
        cell.getCell().itemProperty().addListener(new ChangeListener<RowModel>() {
            @Override
            public void changed(ObservableValue<? extends RowModel> observable, RowModel oldValue, RowModel newValue) {
                if(oldValue != null)
                    imageProperty.unbindBidirectional(oldValue.getImageProperty(getController().getNr()));
                if(newValue != null) {
                    imageProperty.set(newValue.getImageProperty(getController().getNr()).get());
                    imageProperty.bindBidirectional(newValue.getImageProperty(getController().getNr()));
                }
            }
        });
        return new CellImage(imageProperty);
    }

}