package com.presentation.mvc.controllers.table.commands;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.presentation.mvc.models.table.RowModel;


public class DeleteCommand implements CellCommand{
    @Override
    public void invoke(RowModel caller) {
        ServiceSingleton.getInstance().query(new Request(caller.getType(), CRUDType.Delete, caller));
    }
}
