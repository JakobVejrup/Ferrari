package com.presentation.mvc.controllers.customers;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Customer;
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
import java.util.List;
import com.presentation.mvc.controllers.Controller;


public class CustomersController implements Controller{
    private TableModel model;
    private CustomersView view;
    public CustomersController() {
        view = new CustomersView(this::newUser);
        Request request = new Request(ServiceType.Customer, CRUDType.ReadAll, (customers) -> {
        
            Platform.runLater( () -> {
                TableDecorator table = new CustomerTable();
                model = new TableModel(ServiceType.Customer, CustomerModel.makeModelsAsObjects((List<Customer>)customers));
                table = new ParentTableDecorator(model, table);
                table = new TableHeightDecorator(0.6, table);
                table = new TableWidthDecorator(0.8, table);
                if(Facade.getInstance().getLoggedIn().getOccupation() == Occupation.Manager) {
                    table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Opdater andre", new UpdateCommand(), "opdater"), table);
                    table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Slet andre", new DeleteCommand(), "slet"), table);
                }
                view.setTable(table.getTable());
            });
        });
        ServiceSingleton.getInstance().query(request);
    }

    public void newUser(ActionEvent event) {
    
        Facade.getInstance().openModal(new Request(ServiceType.Customer, CRUDType.Create, 
        (customer) -> {
            if(customer != null)
                model.addRow(new RowModel(customer, ServiceType.Customer));
        }));
    }

    @Override
    public CustomersView getView() {
        return view;
    }
}
