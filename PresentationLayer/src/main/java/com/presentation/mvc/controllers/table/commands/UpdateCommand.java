package com.presentation.mvc.controllers.table.commands;


import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.model.threads.Validation;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;
import com.presentation.mvc.controllers.modals.ModalCreator;
import com.presentation.mvc.models.table.RowModel;
import javafx.application.Platform;

public class UpdateCommand implements CellCommand {
    private ModalCreator factory;
    public UpdateCommand(ModalCreator factory) {
        this.factory = factory;
    }
    @Override
    public void invoke(RowModel caller) {
        Facade.getInstance().openModal(factory.makeController(caller));
    }
}
