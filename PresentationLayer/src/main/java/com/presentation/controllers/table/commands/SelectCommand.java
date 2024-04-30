package com.presentation.controllers.table.commands;


import com.presentation.views.table.GuiItem;
import com.presentation.views.table.ui.GuiCell;

public class SelectCommand implements CellCommand {
    @Override
    public void invoke(GuiItem caller, GuiCell cell) {
        //not a sql action rather a gui action, such as i want this object to be the focus in a view or such

    }
}
