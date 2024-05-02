package com.presentation.controllers.table.commands;

import com.logic.ServiceSingleton;
import com.model.enums.CRUDType;
import com.model.threads.Request;
import com.presentation.views.table.GuiItem;
import com.presentation.views.table.ui.GuiCell;

public class DeleteCommand implements CellCommand{
    @Override
    public void invoke(GuiItem caller, GuiCell cell) {
        ServiceSingleton.getInstance().query(new Request(caller.getType(), CRUDType.Delete, caller));
        cell.emptyRow();
    }
}
