package com.presentation.mvc.controllers.table.commands;


import com.model.threads.ActionParameter;
import com.presentation.mvc.models.table.RowModel;

public class SelectCommand implements CellCommand {
    private ActionParameter action;
    public SelectCommand(ActionParameter action) {
        this.action = action;
    }
    @Override
    public void invoke(RowModel caller) {
        //not a sql action rather a gui action, such as i want this object to be the focus in a view or such
        action.action(caller);

    }
}
