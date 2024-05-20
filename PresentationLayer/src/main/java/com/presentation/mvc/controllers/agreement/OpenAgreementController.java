package com.presentation.mvc.controllers.agreement;

import org.controlsfx.tools.Platform;
import com.presentation.mvc.views.agreement.OpenAgreementView;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.table.TableModel;
import javafx.scene.layout.Pane;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;

public class OpenAgreementController implements Controller {
    private TableModel model;
    private OpenAgreementView view;

    public OpenAgreementController() {
        view = new OpenAgreementView(this::newAgreement);
        Request request = new Request(ServiceType.AgreementOpen, CRUDType.ReadAll, (agreements) -> {
            // to allow ui to be run
            Platform.runLater(() -> {
                TableDecorator table = new OpenAgreementTable();
                model = new TableModel(ServiceType.Agreement,
                AgreementModel.makeModelsAsObjects((List<Agreement>) agreements));
                table = new ParentTableDecorator(model, table);
                table = new TableHeightDecorator(0.6, table);
                table = new TableWidthDecorator(0.8, table);
                if (Facade.getInstance().getLoggedIn().getOccupation() == Occupation.Manager) {
                table = new ButtonColumnDecorator(
                new ColumnController(new ButtonFactory(), "Godkend", new UpdateCommand(), "godkend"),table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Slet", new DeleteCommand(), "slet"), table);
                }
                view.setTable(table.getTable());
            });
        });
        ServiceSingleton.getInstance().query(request);
    }

    @Override
    public Pane getView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getView'");
    }

}
