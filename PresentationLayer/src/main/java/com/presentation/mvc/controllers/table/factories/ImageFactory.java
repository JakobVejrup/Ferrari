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
//anders
public class ImageFactory extends NodeFactory{
    private String key;

    public ImageFactory(String key) {
        this.key = key;
    }

    @Override
    public Node createNode(CellController cell) {
        ObjectProperty<byte[]> imageProperty = new SimpleObjectProperty<>();
        cell.itemProperty().addListener(new ChangeListener<RowModel>() {
            @Override
            public void changed(ObservableValue<? extends RowModel> observable, RowModel oldValue, RowModel newValue) {
                if(oldValue != null)
                    imageProperty.unbindBidirectional(oldValue.getImageProperty(key));
                if(newValue != null) {
                    imageProperty.set(newValue.getImageProperty(key).get());
                    imageProperty.bindBidirectional(newValue.getImageProperty(key));
                }
            }
        });
        return new CellImage(imageProperty);
    }

}