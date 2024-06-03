package com.presentation.mvc.views.table.concretes;
import com.model.entities.City;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
//magnus
public class CustomerTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, String> nameCol;
    private TableColumn<RowModel, String> phoneCol;
    private TableColumn<RowModel, String> emailCol;
    private TableColumn<RowModel, String> addressCol;
    private TableColumn<RowModel, City> cityZipCol;
    private TableColumn<RowModel, String> cprCol;

public CustomerTable() {
    getColumns().add(nameCol = new TableColumn<RowModel, String>("Navn"));
    getColumns().add(phoneCol = new TableColumn<RowModel, String>("Tlf Nr"));
    getColumns().add(emailCol = new TableColumn<RowModel, String>("Email"));
    getColumns().add(addressCol = new TableColumn<RowModel, String>("Adresse"));
    getColumns().add(cityZipCol = new TableColumn<RowModel, City>("Postnr"));
    getColumns().add(cprCol = new TableColumn<RowModel, String>("Cpr Nr"));

    nameCol.setCellValueFactory(cellData -> ((CustomerModel)cellData.getValue().getItem()).nameProperty());
    nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
    phoneCol.setCellValueFactory(cellData -> ((CustomerModel)cellData.getValue().getItem()).phoneNumberProperty());
    emailCol.setCellValueFactory(cellData -> ((CustomerModel)cellData.getValue().getItem()).emailProperty());
    addressCol.setCellValueFactory(cellData -> ((CustomerModel)cellData.getValue().getItem()).addressProperty());
    cityZipCol.setCellValueFactory(cellData -> ((CustomerModel)cellData.getValue().getItem()).cityProperty());
    cprCol.setCellValueFactory(cellData -> ((CustomerModel)cellData.getValue().getItem()).CprProperty());
}
    @Override
    public GuiTable getTable() {
        return this;
    }
   }