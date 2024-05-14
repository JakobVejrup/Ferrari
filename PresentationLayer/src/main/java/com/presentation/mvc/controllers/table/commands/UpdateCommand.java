package com.presentation.mvc.controllers.table.commands;


import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.model.threads.Validation;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;
import com.presentation.mvc.models.table.RowModel;
import javafx.application.Platform;

public class UpdateCommand implements CellCommand {
    @Override
    public void invoke(RowModel caller) {
        Request request = new Request(caller.getType(), CRUDType.Update, caller.getItem(),
                new Validation(
                        (requestVal) -> {
                            Validation validation = ((Request) requestVal).getValidation();
                            Platform.runLater(
                                    () -> Alerter.information("Forkerte data", validation.getMessages())
                            );
                        }
                )
        );
        Facade.getInstance().openModal(request);
    }
}
