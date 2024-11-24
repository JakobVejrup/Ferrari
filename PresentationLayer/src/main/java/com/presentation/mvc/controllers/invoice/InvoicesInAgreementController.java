package com.presentation.mvc.controllers.invoice;

import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.views.table.concretes.InvoiceTable;
import com.presentation.mvc.views.table.decorators.ParentTableDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.decorators.TableHeightDecorator;
import com.presentation.mvc.views.table.decorators.TableWidthDecorator;
import javafx.scene.layout.VBox;

//table for invoices in agreement
public class InvoicesInAgreementController extends Controller{
    private TableModel model;
    private VBox view;
    public InvoicesInAgreementController(TableModel model) {
        TableDecorator table = new InvoiceTable();
        table = new ParentTableDecorator(model, table);
        table = new TableHeightDecorator(0.3, table);
        table = new TableWidthDecorator(0.8, table);
        view = new VBox();
        table.getTable().setup(view);
        setView(view);
    }
}
