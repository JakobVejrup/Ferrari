package com.presentation.mvc.controllers.table.commands;

import com.presentation.mvc.models.table.RowModel;

@FunctionalInterface
public interface CellCommand {
    public void invoke(RowModel caller);
}
