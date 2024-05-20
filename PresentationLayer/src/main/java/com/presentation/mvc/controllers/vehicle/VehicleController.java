package com.presentation.mvc.controllers.vehicle;

import java.util.List;

import com.model.entities.Vehicle;
import com.presentation.mvc.models.vehicle.VehicleModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VehicleController {
    private ObservableList<VehicleModel> vehicleModels;
    private ObjectProperty<VehicleModel> selectedVehicle;

    public VehicleController(List<Vehicle> vehicles) {
        this.vehicleModels = FXCollections.observableArrayList(VehicleModel.makeModels(vehicles));
        this.selectedVehicle = new SimpleObjectProperty<>();
    }
    public ObservableList<VehicleModel> getVehicleModels() {
        return vehicleModels;
    }
    public ObjectProperty<VehicleModel> selectedVehicleProperty() {
        return selectedVehicle;
    }
    public VehicleModel getVehicleModel() {
        return selectedVehicle.get();
    }
    public void addVehicle(Vehicle vehicle) {
        VehicleModel vehicleModel = new VehicleModel();
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
