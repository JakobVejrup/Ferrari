package com.presentation.mvc.controllers.employee;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;
import com.model.entities.Employee;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.CellCommand;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.controllers.table.factories.CheckboxFactory;
import com.presentation.mvc.controllers.table.factories.tables.OpenAgreementFactory;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.employee.EmployeesView;
import com.presentation.mvc.views.table.concretes.EmployeeTable;
import com.presentation.mvc.views.table.decorators.*;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.presentation.tools.facade.Facade;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.employee.modals.CreateEmployeeController;
import com.presentation.mvc.controllers.employee.modals.UpdateEmployeeController;

public class EmployeesController extends Controller {
    private TableModel model;
    private EmployeesView view;
    private TableDecorator table;

    public EmployeesController() {
        Button newUserButton = new Button("Ny bruger");
        newUserButton.setOnAction(this::newUser);
        table = new EmployeeTable();
        view = new EmployeesView(newUserButton);
        Request request = new Request(ServiceType.Employee, CRUDType.ReadAll, (employees) -> {
            //to allow ui to be run
            Platform.runLater( () -> {
                Request getAgreements = new Request(ServiceType.AgreementOpen, CRUDType.ReadAll);
                List<Agreement> agreements = (List<Agreement>)ServiceSingleton.getInstance().queryNoThread(getAgreements);
                model = new TableModel(ServiceType.Employee, EmployeeModel.makeModelsAsObjects((List<Employee>)employees));
                model.getRows().iterator().forEachRemaining((row) -> {
                    row.getImages().put("Ansat", ((EmployeeModel)row.getItem()).imageProperty());
                });
                table = new TableHeightDecorator(0.6, table);
                table = new TableWidthDecorator(0.8, table);
                if(Facade.getInstance().getLoggedIn().getOccupation() == Occupation.Manager) {
                    table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Opdater andre", new UpdateCommand((row) -> new UpdateEmployeeController((Employee)row.getItem())), "opdater"), table);
                    table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Slet andre", new DeleteCommand(), "slet"), table);
                    table = new CheckboxColumnDecorator(new UpdateCommand((row) -> new UpdateEmployeeController((Employee)row.getItem())), "Slet", "Slet", "Slet Alle", table);
                }
                table = new ParentTableDecorator(model, table);
                if(agreements != null) {
                    for(RowModel row : model.getRows()) {
                        ArrayList<Agreement> toAdd = new ArrayList<>();
                        for(Agreement agreement : agreements)
                            if(((Employee)row.getItem()).getId() == agreement.getCustomer().getId())
                                toAdd.add(agreement);
                        row.getItems().put(ServiceType.AgreementOpen, new TableModel(ServiceType.AgreementOpen, OpenAgreementsModel.makeModelsAsObjects(toAdd)));
                        agreements.removeAll(toAdd);
                    }
                    table = new ChildTableDecorator(new ColumnController(new OpenAgreementFactory(), "Tilbud"), table);
                }
                table.getTable().setup(view);
            });

        });
        ServiceSingleton.getInstance().query(request);
    }

    public void newUser(ActionEvent event) {
        Object employee = Facade.getInstance().openModalResult(new CreateEmployeeController());
        if(employee != null)
            model.addRow(new RowModel(employee, ServiceType.Employee));
    }
    @Override
    public EmployeesView getView() {
        return view;
    }
}
