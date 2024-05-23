package com.presentation.mvc.controllers.invoice;

import java.util.List;
import com.logic.services.enums.ServiceType;
import com.model.entities.Invoice;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.Invoice.InvoiceModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.invoice.InvoicesInAgreementView;
import com.presentation.mvc.views.table.concretes.InvoiceTable;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;

import javafx.scene.layout.Pane;

public class InvoicesInAgreementController extends Controller{
    private TableModel model;
    private InvoicesInAgreementView view;
    public InvoicesInAgreementController(List<Invoice> invoices) {
        TableDecorator table = new InvoiceTable();
        model = new TableModel(ServiceType.Invoice, InvoiceModel.makeModelsAsObjects(invoices));
        table = new ParentTableDecorator(model, table);
        table = new TableHeightDecorator(0.8, table);
        table = new TableWidthDecorator(0.8, table);
        view = new InvoicesInAgreementView(table);
        setView(view);
    }

    
}
