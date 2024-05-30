package com.presentation.mvc.controllers.table.factories.tables;

import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.agreement.AgreementController;
import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.SelectCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.concretes.MiniAgreementTable;
import com.presentation.mvc.views.table.concretes.OpenAgreementTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import com.presentation.tools.facade.Facade;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class OpenAgreementFactory extends NodeFactory {
    @Override
    public Node createNode(CellController controller) {
        VBox box = new VBox();
        GuiTable table = new MiniAgreementTable();
        table = TableFactory.readyTable(controller, ServiceType.AgreementOpen, table);
        table.setup(box);
        table = new ButtonColumnDecorator(new ColumnController(new ButtonFactory("acceptButton"), "Se aftale", new SelectCommand(
            (rowModel) -> {
                AgreementModel model = (AgreementModel)(((RowModel)rowModel).getItem());
                Facade.getInstance().setCenter(new AgreementController(model, true, true).getView());
            }
            ), "se "), (TableDecorator) table).getTable();
        return table;
    }
}
