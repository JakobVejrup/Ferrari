package com.presentation.controllers.table.commands;


import com.logic.ServiceSingleton;
import com.model.enums.CRUDType;
import com.model.threads.Request;
import com.presentation.controllers.guiloading.GuiFacade;
import com.presentation.views.table.GuiItem;
import com.presentation.views.table.ui.GuiCell;

public class UpdateCommand implements CellCommand {
    @Override
    public void invoke(GuiItem caller, GuiCell cell) {
        Request request = new Request(caller.getType(), CRUDType.Update, caller);
        Object obj = GuiFacade.getInstance().openModal(request);
        if(obj.equals(request.getObject()))
            return;
        request.setObject(obj);
        ServiceSingleton.getInstance().query(request);
    }
}
