package com.presentation.mvc.controllers.customers;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.customer.CustomersView;
import com.presentation.mvc.views.table.concretes.CustomerTable;
import com.presentation.mvc.views.table.decorators.*;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.List;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.customers.modals.CreateCustomerController;
import com.presentation.mvc.controllers.customers.modals.UpdateCustomerController;
import com.presentation.mvc.controllers.employee.modals.CreateEmployeeController;
import com.presentation.mvc.controllers.employee.modals.UpdateEmployeeController;

//magnus
public class CustomersController extends Controller{
    private TableModel model;
    private CustomersView view;

    public CustomersController() {
        Button newCustomer = new Button("Ny Bruger");
        newCustomer.setOnAction(this::newCustomer);
        view = new CustomersView(newCustomer);
        Request request = new Request(ServiceType.Customer, CRUDType.ReadAll, (customers) -> {
        
            Platform.runLater( () -> {
                TableDecorator table = new CustomerTable();
                model = new TableModel(ServiceType.Customer, CustomerModel.makeModelsAsObjects((List<Customer>)customers));
                table = new ParentTableDecorator(model, table);
                table = new TableHeightDecorator(0.8, table);
                table = new TableWidthDecorator(0.9, table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory("optionButton"), "Opdater kunde", new UpdateCommand((row) -> new UpdateCustomerController((Customer)row.getItem())), "opdater"), table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory("declineButton"), "Slet kunde", new DeleteCommand(model), "slet"), table);
                view.setTable(table.getTable());
            });
        });
        ServiceSingleton.getInstance().query(request);
        setView(view);
    }

    public void newCustomer(ActionEvent event) {
        Object customer = Facade.getInstance().openModalResult(new CreateCustomerController());
        if(customer != null)
            model.addRow(new RowModel(customer, ServiceType.Customer));
    }

    @Override
    public CustomersView getView() {
        return view;
    }
}
