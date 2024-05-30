package com.presentation.mvc.controllers.customers.modals;

import java.util.List;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.SelectCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.models.vehicle.VehicleModel;
import com.presentation.mvc.views.customer.CustomersView;
import com.presentation.mvc.views.table.concretes.CustomerTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import com.presentation.tools.ScreenWatcher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import com.model.entities.Customer;
//magnus
public class SelectCustomersController extends ModalController {
    private TableModel model;
    private CustomersView view;
    private TableDecorator table;
    public SelectCustomersController() {
        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        table = new CustomerTable();
        view = new CustomersView(cancelButton);
        Request request = new Request(ServiceType.Customer, CRUDType.ReadAll, (customers) -> {
            //to allow ui to be run
            Platform.runLater( () -> {
                model = new TableModel(ServiceType.Employee, CustomerModel.makeModelsAsObjects((List<Customer>)customers));
                table = new ParentTableDecorator(model, table);
                table = new TableHeightDecorator(0.6, table);
                table = new TableWidthDecorator(0.8, table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory("acceptButton"), "Vælg kunde", new SelectCommand( 
                    (rowModel) -> {
                        setResult(((RowModel)rowModel).getItem()); 
                        close();    
                    }),
                        "Vælg"), table);
                table.getTable().setup(view);
            });
        });
        view.setPrefHeight(ScreenWatcher.getInstance().getScreenHeightWithDecimal(0.6));
        view.setPrefWidth(ScreenWatcher.getInstance().getScreenWidthWithDecimal(0.8));
        ServiceSingleton.getInstance().query(request);
    }

    @Override
    public Pane getView() {
        return view;
    }
    
    public void decline(ActionEvent event) {
        close();
    }
}