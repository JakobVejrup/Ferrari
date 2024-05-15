package com.presentation.mvc.controllers.vehicle;

import java.util.List;

import com.model.entities.Vehicle;
import com.presentation.mvc.models.vehicle.VehicleModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehicleController {
    private ObservableList<VehicleModel> vehicleModels;

    public VehicleController(List<Vehicle> vehicels) {
        this.vehicleModels = FXCollections.observableArrayList(VehicleModel.makeModels(vehicels));
    }
    public ObservableList<VehicleModel> getVehicleModels() {
        return vehicleModels;
    }
    public void addVehicle(Vehicle vehicle) {
        VehicleModel vehicleModel = new VehicleModel(vehicle);
        vehicleModels.add(vehicleModel);
    }
    public void updateVehicle(VehicleModel vehicleModel, Vehicle updatedVehicle) {
        vehicleModel.setVehicleID(updatedVehicle.getVehicleID());
        vehicleModel.setVehicleName(updatedVehicle.getVehicleName());
        vehicleModel.setPrice(updatedVehicle.getPrice());
    }
    public void deleteVehicle(VehicleModel vehicleModel) {
        vehicleModels.remove(vehicleModel);
    }
    
}
