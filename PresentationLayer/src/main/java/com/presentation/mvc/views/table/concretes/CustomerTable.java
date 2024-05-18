/*package com.presentation.mvc.views.table.concretes;

import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class CustomerTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, String> nameCol;
    private TableColumn<RowModel, String> phoneCol;
    private TableColumn<RowModel, String> emailCol;
    private TableColumn<RowModel, String> addressCol;
    private TableColumn<RowModel, String> cityZipCol;
    private TableColumn<RowModel, String> cprCol;

    public CustomerTable() {
        getColumns().add(nameCol = new TableColumn<RowModel, String>("Navn"));
        getColumns().add(phoneCol = new TableColumn<RowModel, String>("Tlf Nr"));
        getColumns().add(emailCol = new TableColumn<RowModel, String>("Email"));
        getColumns().add(addressCol = new TableColumn<RowModel, String>("Adresse"));
        getColumns().add(cityZipCol = new TableColumn<RowModel, String>("Postnr"));
        getColumns().add(cprCol = new TableColumn<RowModel, String>("Cpr Nr"));
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((CustomerModel)column.getValue().getItem()).nameProperty();
            }
        });
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((CustomerModel)column.getValue().getItem()).phoneNumberProperty();
            }
        });
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((CustomerModel)column.getValue().getItem()).emailProperty();
            }
        });
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((CustomerModel)column.getValue().getItem()).addressProperty();
            }
        });
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cityZipCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((CustomerModel)column.getValue().getItem()).CityZipProperty();
            }
        });
        cityZipCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cprCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RowModel, String> column) {
                return ((CustomerModel)column.getValue().getItem()).CprProperty();
            }
        });
    }

    @Override
    public GuiTable getTable() {
        return this;
    }
    
}
*/

package com.presentation.mvc.views.table.concretes;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.views.table.decorators.TableDecorator;
import com.presentation.mvc.views.table.ui.GuiTable;

import javafx.scene.control.TableColumn;

public class CustomerTable extends GuiTable implements TableDecorator {
    private TableColumn<RowModel, String> nameCol;
    private TableColumn<RowModel, String> phoneCol;
    private TableColumn<RowModel, String> emailCol;
    private TableColumn<RowModel, String> addressCol;
    private TableColumn<RowModel, String> cityZipCol;
    private TableColumn<RowModel, String> cprCol;

public CustomerTable() {
    getColumns().add(nameCol = new TableColumn<RowModel, String>("Navn"));
    getColumns().add(phoneCol = new TableColumn<RowModel, String>("Tlf Nr"));
    getColumns().add(emailCol = new TableColumn<RowModel, String>("Email"));
    getColumns().add(addressCol = new TableColumn<RowModel, String>("Adresse"));
    getColumns().add(cityZipCol = new TableColumn<RowModel, String>("Postnr"));
    getColumns().add(cprCol = new TableColumn<RowModel, String>("Cpr Nr"));

    nameCol.setCellValueFactory(column -> ((CustomerModel)column.getValue().getItem()).nameProperty());
    phoneCol.setCellValueFactory(column -> ((CustomerModel)column.getValue().getItem()).phoneNumberProperty());
    emailCol.setCellValueFactory(column -> ((CustomerModel)column.getValue().getItem()).emailProperty());
    addressCol.setCellValueFactory(column -> ((CustomerModel)column.getValue().getItem()).addressProperty());
    cityZipCol.setCellValueFactory(column -> ((CustomerModel)column.getValue().getItem()).CityZipProperty());
    cprCol.setCellValueFactory(column -> ((CustomerModel)column.getValue().getItem()).CprProperty());
}
    @Override
    public GuiTable getTable() {
        return this;
    }
   }