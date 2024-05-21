package com.presentation.mvc.controllers.employee.modals;

import java.util.ArrayList;
import java.util.List;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;
import com.model.entities.Employee;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.modals.ModalController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.SelectCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.controllers.table.factories.tables.OpenAgreementFactory;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.employee.EmployeesView;
import com.presentation.mvc.views.table.concretes.EmployeeTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.CheckboxColumnDecorator;
import com.presentation.mvc.views.table.decorators.ChildTableDecorator;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.presentation.tools.facade.Facade;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SelectEmployeeController extends ModalController {
    private TableModel model;
    private EmployeesView view;
    private TableDecorator table;
    public SelectEmployeeController(Stage stage) {
        super(stage);
        Button cancelButton = new Button("Fortryd");
        cancelButton.setOnAction(this::decline);
        table = new EmployeeTable();
        view = new EmployeesView(cancelButton);
        Request request = new Request(ServiceType.Employee, CRUDType.ReadAll, (employees) -> {
            //to allow ui to be run
            Platform.runLater( () -> {
                model = new TableModel(ServiceType.Employee, EmployeeModel.makeModelsAsObjects((List<Employee>)employees));
                model.getRows().iterator().forEachRemaining((row) -> {
                    row.getImages().put("Ansat", ((EmployeeModel)row.getItem()).imageProperty());
                });
                table = new ParentTableDecorator(model, table);
                table = new TableHeightDecorator(0.6, table);
                table = new TableWidthDecorator(0.8, table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Opdater andre", new SelectCommand( 
                    (rowModel) -> {
                        setResult(((RowModel)rowModel).getItem()); 
                        close();    
                    }),
                        "Vælg"), table);
                table.getTable().setup(view);
            });
        });
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