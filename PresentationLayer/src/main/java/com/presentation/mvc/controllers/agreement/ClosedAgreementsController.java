package com.presentation.mvc.controllers.agreement;

import java.util.List;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.DeleteCommand;
import com.presentation.mvc.controllers.table.commands.SelectCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.agreement.AgreementView;
import com.presentation.tools.facade.Facade;
import com.presentation.mvc.views.table.concretes.ClosedAgreementsTable;
import com.presentation.mvc.views.table.concretes.OpenAgreementTable;
import com.presentation.mvc.views.table.decorators.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ClosedAgreementsController extends Controller{
    private TableModel model;
    private AgreementView view;
    public ClosedAgreementsController() {
        view = new AgreementView();
        Request request = new Request(ServiceType.AgreementClosed, CRUDType.ReadAll, (agreements) -> {
            Platform.runLater( () -> {
                TableDecorator table = new ClosedAgreementsTable();
                model = new TableModel(ServiceType.AgreementClosed, AgreementModel.makeModelsAsObjects((List<Agreement>)agreements));
                table = new ParentTableDecorator(model, table);
                table = new TableHeightDecorator(0.6, table);
                table = new TableWidthDecorator(0.8, table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Se Aftale", new SelectCommand((rowmodel)->{
                    AgreementModel agreement = (AgreementModel)((RowModel)rowmodel).getItem();
                    Facade.getInstance().setCenter(new AgreementController(agreement, false).getView());
                }), "Se Aftale"), table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Slet aftale", new DeleteCommand(model), "slet"), table);
                table.getTable().setup(view);
            });
        });
        ServiceSingleton.getInstance().query(request);
        setView(view);

    }


    @Override
    public AgreementView getView() {
        return view;
    }   
}
