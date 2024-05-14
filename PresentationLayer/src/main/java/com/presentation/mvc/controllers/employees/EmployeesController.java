package com.presentation.mvc.controllers.employees;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Employee;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.employee.EmployeesView;
import com.presentation.mvc.views.table.concretes.EmployeeTable;
import com.presentation.mvc.views.table.decorators.*;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.util.List;
import com.presentation.mvc.controllers.Controller;

public class EmployeesController implements Controller{
    private TableModel model;
    private EmployeesView view;
    public EmployeesController() {
        view = new EmployeesView(this::newUser);
        Request request = new Request(ServiceType.Employee, CRUDType.ReadAll, (employees) -> {
            //to allow ui to be run
            Platform.runLater( () -> {
                TableDecorator table = new EmployeeTable();
                model = new TableModel(ServiceType.Employee, (List<Object>) employees);
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
    
        Facade.getInstance().openModal(new Request(ServiceType.Employee, CRUDType.Create, 
        (employee) -> {
            if(employee != null)
                model.addRow(new RowModel(employee, ServiceType.Employee));
        }));
    }
    public EmployeesView getView() {
        return view;
    }
}
