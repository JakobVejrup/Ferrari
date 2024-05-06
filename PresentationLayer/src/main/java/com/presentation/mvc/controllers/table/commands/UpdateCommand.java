package com.presentation.mvc.controllers.table.commands;


import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.services.enums.CRUDType;
import com.presentation.tools.facade.Facade;
import com.presentation.mvc.models.table.RowModel;

public class UpdateCommand implements CellCommand {
    @Override
    public void invoke(RowModel caller) {
        Request request = new Request(caller.getType(), CRUDType.Update, true, caller);
        Object obj = Facade.getInstance().openModal(request);
        if(obj.equals(request.getObject()))
            return;
        request.setObject(obj);
        ServiceSingleton.getInstance().query(request);
    }
}
