package com.presentation.controllers.table.commands;


import com.presentation.views.table.GuiItem;
import com.presentation.views.table.ui.GuiCell;

public interface CellCommand {
    public void invoke(GuiItem caller, GuiCell cell);
}
