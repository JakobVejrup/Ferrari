package com.presentation.mvc.controllers.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;
import com.model.entities.Employee;
import com.model.entities.Vehicle;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.employee.modals.UpdateEmployeeController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.controllers.table.factories.tables.OpenAgreementFactory;
import com.presentation.mvc.controllers.vehicle.modals.CreateVehicleController;
import com.presentation.mvc.controllers.vehicle.modals.UpdateVehicleController;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.table.concretes.VehicleTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.CheckboxColumnDecorator;
import com.presentation.mvc.views.table.decorators.ChildTableDecorator;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import com.presentation.mvc.views.vehicle.VehiclesView;
import com.presentation.tools.facade.Facade;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class AllVehiclesController extends Controller {
    private TableModel model;
    private VehiclesView view;
    private TableDecorator table;
    public AllVehiclesController() {

        Button insertButton = new Button("Ny bil");
        insertButton.setOnAction(this::addVehicle);

        view = new VehiclesView(insertButton);
        setView(view);
        table = new VehicleTable();
        Request request = new Request(ServiceType.Vehicle, CRUDType.ReadAll, (vehicles) -> {
            //to allow ui to be run
            Platform.runLater( () -> {
                model = new TableModel(ServiceType.Employee, VehicleModel.makeModelsAsObjects((List<Vehicle>)vehicles));
                model.getRows().iterator().forEachRemaining((row) -> {
                    row.getImages().put("vehicle", ((VehicleModel)row.getItem()).imageProperty());
                });
                table = new TableHeightDecorator(0.8, table);
                table = new TableWidthDecorator(0.9, table);
                if(Facade.getInstance().getLoggedIn().getOccupation() == Occupation.Manager) {
                    table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory("optionButton"), "Opdater andre", new UpdateCommand(
                        (row) -> new UpdateVehicleController((Vehicle)row.getItem())
                        ), "opdater"), table);
                    table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory("declineButton"), "Slet andre", new DeleteCommand(model), "slet"), table);
                    table = new CheckboxColumnDecorator(new DeleteCommand(model), "Slet", "Slet", "Slet Alle", table);
                }
                table = new ParentTableDecorator(model, table);
                table.getTable().setup(view);
            });

        });
        ServiceSingleton.getInstance().query(request);
    }

    public void addVehicle(ActionEvent event) {
        Object vehicle = Facade.getInstance().openModalResult(new CreateVehicleController());
        if (vehicle != null) {
            RowModel newVehicle = new RowModel(vehicle, ServiceType.Vehicle);
            newVehicle.getImages().put("vehicle", ((VehicleModel)newVehicle.getItem()).imageProperty());
            model.addRow(newVehicle);            
        }

    }

}

// table model
// table.getTable.setup. 