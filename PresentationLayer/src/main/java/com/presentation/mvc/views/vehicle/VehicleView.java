package com.presentation.mvc.views.vehicle;

import com.presentation.mvc.controllers.vehicle.VehicleController;
import com.presentation.mvc.models.vehicle.VehicleModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;



public class VehicleView {
    private VehicleController viewModel;
    private TableView<VehicleModel> tableView;

    public VehicleView(VehicleController viewModel) {
        this.viewModel = viewModel;
        tableView = new TableView<>();

        TableColumn<VehicleModel, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().vehicleIdProperty());
    
        TableColumn<VehicleModel, String> nameCol = new TableColumn<>("BilNavn");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<VehicleModel, Number> priceCol = new TableColumn<>("Pris");
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        

        tableView.getColumns().addAll(idCol, nameCol, priceCol);
        tableView.setItems(viewModel.getVehicleModels());
    }

    public TableView<VehicleModel> getTableView() {
        return tableView;
    }
}

