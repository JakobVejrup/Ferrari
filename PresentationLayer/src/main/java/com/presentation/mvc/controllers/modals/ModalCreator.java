package com.presentation.mvc.controllers.modals;

import com.presentation.mvc.models.table.RowModel;
//anders
public interface ModalCreator {
    public ModalController makeController(RowModel caller);
}
