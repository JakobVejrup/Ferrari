package com.presentation.mvc.controllers.agreement;

import java.sql.Date;
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
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.agreement.AgreementView;
import com.presentation.tools.facade.Facade;
import com.presentation.mvc.views.table.concretes.OpenAgreementTable;
import com.presentation.mvc.views.table.decorators.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class OpenAgreementsController extends Controller{
    private  TableModel model;
    private AgreementView view;
    public OpenAgreementsController() {
        view = new AgreementView(this::newAgreement);
        Request request = new Request(ServiceType.AgreementOpen, CRUDType.ReadAll, (agreements) -> {
            Platform.runLater( () -> {
                TableDecorator table = new OpenAgreementTable();
                model = new TableModel(ServiceType.AgreementOpen, OpenAgreementsModel.makeModelsAsObjects((List<Agreement>)agreements));
                table = new ParentTableDecorator(model, table);
                table = new TableHeightDecorator(0.6, table);
                table = new TableWidthDecorator(0.8, table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Opdater aftale", new SelectCommand((rowmodel)->{
                    OpenAgreementsModel agreement = (OpenAgreementsModel)((RowModel)rowmodel).getItem();
                    Facade.getInstance().setCenter(new OpenAgreementController(agreement).getView());
                }), "opdater"), table);
                table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory(), "Slet aftale", new DeleteCommand(model), "slet"), table);
                view.setTable(table.getTable());
            });
        });
        ServiceSingleton.getInstance().query(request);
        setView(view);

        Button makeAgreement = new Button("opdatere aftale");
        makeAgreement.setOnAction(this::newAgreement);
        view.addButtons(makeAgreement);
        
    }

    public void newAgreement(ActionEvent event) {
        OpenAgreementsModel agreement = new OpenAgreementsModel();
        agreement.setStartAgreement(new Date(System.currentTimeMillis()));
        Facade.getInstance().setCenter(new OpenAgreementController(new OpenAgreementsModel()).getView());
    }
    @Override
    public AgreementView getView() {
        return view;
    }   
}
