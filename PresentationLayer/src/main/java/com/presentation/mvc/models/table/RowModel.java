package com.presentation.mvc.models.table;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.List;

import com.logic.services.enums.ServiceType;
import com.model.threads.ActionParameter;

//Item holder class used to contain the item AND its boolean, so it will remember if the checkbox should be checked
public class RowModel {
    private ServiceType type;
    private Object item;
    //keeps account of the different columns and their possible checkbox bools
    private HashMap<Integer, BooleanProperty> properties;
    private HashMap<ServiceType, TableModel> items;
    private HashMap<String, ObjectProperty<byte[]>> images;

    public RowModel(Object item, ServiceType type, TableModel... tables) {
        this.type = type;
        this.item = item;
        properties = new HashMap<>();
        items = new HashMap<>();
        images = new HashMap<>();
        for(TableModel table : tables)
            items.put(table.getType(), table);
    }

    public ServiceType getType() {
        return type;
    }
    public Object getItem() {
        return item;
    }
    public BooleanProperty getProperty(int i) {
        if(properties.get(i) == null)
            properties.put(i, new SimpleBooleanProperty());
        return properties.get(i);
    }
    public ObjectProperty<byte[]> getImageProperty(String i) {
        if(images.get(i) == null)
            images.put(i, new SimpleObjectProperty<byte[]>());
        return images.get(i);
    }
    public HashMap<ServiceType, TableModel> getItems() {
        return items;
    }
    public HashMap<String, ObjectProperty<byte[]>> getImages() {
        return images;
    }
    public static ObservableList<RowModel> makeRowModels(ServiceType type, List<Object> objects) {
        ObservableList<RowModel> list = FXCollections.observableArrayList();
        for(Object obj : objects)
            list.add(new RowModel(obj, type));
        return list;
    }
}
