package com.presentation.mvc.controllers.table.commands;

import com.presentation.mvc.controllers.modals.ModalCreator;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.tools.facade.Facade;

// command to update table items
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
