package com.presentation.mvc.views.invoice;

import com.presentation.mvc.views.table.decorators.TableDecorator;

import javafx.scene.layout.VBox;

public class InvoicesInAgreementView extends VBox{
    public InvoicesInAgreementView(TableDecorator table) {
        super(table.getTable());
    }
}
