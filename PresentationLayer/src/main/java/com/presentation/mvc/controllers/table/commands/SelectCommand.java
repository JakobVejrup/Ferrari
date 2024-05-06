package com.presentation.mvc.controllers.table.commands;


import com.presentation.mvc.models.table.RowModel;

public class SelectCommand implements CellCommand {
    @Override
    public void invoke(RowModel caller) {
        //not a sql action rather a gui action, such as i want this object to be the focus in a view or such

    }
}
