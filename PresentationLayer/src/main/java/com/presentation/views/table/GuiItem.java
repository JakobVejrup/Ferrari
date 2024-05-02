package com.presentation.views.table;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.HashMap;
import java.util.List;

import com.model.enums.ServiceType;
//Item holder class used to contain the item AND its boolean, so it will remember if the checkbox should be checked
public class GuiItem {
    private ServiceType type;
    private Object item;
    private HashMap<Integer, BooleanProperty> properties;
    private HashMap<String, ObservableList<GuiItem>> items;


    public GuiItem(Object item, ServiceType type) {
        this.type = type;
        this.item = item;
        properties = new HashMap<>();
        items = new HashMap<>();
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
    public HashMap<String, ObservableList<GuiItem>> getItems() {
        return items;
    }
    public static ObservableList<GuiItem> getGuiItems(List<Object> objects, ServiceType type) {
        ObservableList<GuiItem> list = FXCollections.observableArrayList();
        for(Object obj : objects)
            list.add(new GuiItem(obj, type));
        return list;
    }
}
