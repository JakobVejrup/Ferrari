package com.presentation.mvc.controllers.table.factories.tables;

import com.logic.services.enums.ServiceType;
import com.presentation.mvc.controllers.table.CellController;
import com.presentation.mvc.controllers.table.ColumnController;
import com.presentation.mvc.controllers.table.commands.SelectCommand;
import com.presentation.mvc.controllers.table.commands.UpdateCommand;
import com.presentation.mvc.controllers.table.factories.ButtonFactory;
import com.presentation.mvc.controllers.table.factories.NodeFactory;
import com.presentation.mvc.views.table.concretes.EmployeeTable;
import com.presentation.mvc.views.table.decorators.ButtonColumnDecorator;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
//karl
public class EmployeeTableFactory extends NodeFactory {
    @Override
    public Node createNode(CellController controller) {
        VBox box = new VBox();
        GuiTable table = new EmployeeTable();
        table = TableFactory.readyTable(controller, ServiceType.Employee, table);
        table.setup(box);
        return table;
    }
}
