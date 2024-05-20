package com.presentation.mvc.controllers.vehicle;

import java.util.List;

import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.tools.facade.Facade;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AllVehiclesController implements Controller {
    private TableModel model;
    private VBox view;

    public AllVehiclesController() {

        Button insertButton = new Button("lav bil");
        insertButton.setOnAction(this::addVehicle);

        view = new VBox(insertButton);
        VehicleTable
    }

    public void addVehicle(ActionEvent event) {
        Facade.getInstance().openModal(new Request(
            ServiceType.Vehicle,
            CRUDType.Create,
            (Vehicle) -> {
                if (Vehicle != null)
                    model.addRow(new RowModel(Vehicle, ServiceType.Vehicle));
            }

        ));

    }

    @Override
    public Pane getView() {
        return view;
    }
    
}

// table model
// table.getTable.setup. 