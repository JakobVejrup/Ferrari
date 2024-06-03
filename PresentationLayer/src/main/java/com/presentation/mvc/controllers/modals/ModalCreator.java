package com.presentation.mvc.controllers.modals;

import com.presentation.mvc.models.table.RowModel;
//anders
//for update command needs to know how to make a modal
@FunctionalInterface
public interface ModalCreator {
    public ModalController makeController(RowModel caller);
}
