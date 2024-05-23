package com.presentation.mvc.controllers.table.commands;

import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;


public class DeleteCommand implements CellCommand{
    private TableModel table;
    public DeleteCommand(TableModel table) {
        this.table = table;
    }
    @Override
    public void invoke(RowModel caller) {
        ServiceSingleton.getInstance().query(new Request(caller.getType(), CRUDType.Delete, caller, (wasRemoved) -> {
            if ((boolean)wasRemoved) {
                table.removeRow(caller);
            }
        }));
    }
}
