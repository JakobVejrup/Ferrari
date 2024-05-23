package com.presentation.mvc.controllers.modals;

import com.presentation.mvc.models.table.RowModel;

public interface ModalCreator {
    public ModalController makeController(RowModel caller);
}
