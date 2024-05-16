

public class OpenAgreementTable extends GuiTable implements TableDecorator {
private TableColumn<RowModel, Int> fixedTermsCol;
private TableColumn<RowModel, Double> startValueCol;
private TableColumn<RowModel, Date> startAgreementCol;
private TableColumn<RowModel, Rating> RKiCol;
private TableColumn<RowModel, Customer> customerCol;
private TableColumn<RowModel, Employee> employeeCol;
private TableColumn<RowModel, Vehicle> vehicleCol;

public OpenAgreementTable() {
    getColumns().add(fixedTermsCol = new TableColumn<RowModel, Int>("Fixed Terms"));
    getColumns().add(startValueCol = new TableColumn<RowModel, Double>("Start Value"));
    getColumns().add(startAgreementCol = new TableColumn<RowModel, Date>("Start Agreement"));
    getColumns().add(RKiCol = new TableColumn<RowModel, Rating>("RKi"));
    getColumns().add(customerCol = new TableColumn<RowModel, Customer>("Customer"));
    getColumns().add(employeeCol = new TableColumn<RowModel, Employee>("Employee"));
    getColumns().add(vehicleCol = new TableColumn<RowModel, Vehicle>("Vehicle"));
    fixedTermsCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Int>, ObservableValue<Int>>() {
        @Override
        public ObservableValue<Int> call(TableColumn.CellDataFeatures<RowModel, Int> column) {
            return ((AgreementModel)column.getValue().getItem()).fixedTermsProperty();
        }
    });
    fixedTermsCol.setCellFactory(TextFieldTableCell.forTableColumn());
    startValueCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Double>, ObservableValue<Double>>() {
        @Override
        public ObservableValue<Double> call(TableColumn.CellDataFeatures<RowModel, Double> column) {
            return ((AgreementModel)column.getValue().getItem()).startValueProperty();
        }
    });
    fixedTermsCol.setCellFactory(TextFieldTableCell.forTableColumn());
    startAgreementCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Date>, ObservableValue<Date>>() {
        @Override
        public ObservableValue<Date> call(TableColumn.CellDataFeatures<RowModel, Date> column) {
            return ((AgreementModel)column.getValue().getItem()).startAgreementProperty();
        }
    });
    startAgreement.setCellFactory(TextFieldTableCell.forTableColumn());
    RKiCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Rating>, ObservableValue<Rating>>() {
        @Override
        public ObservableValue<Rating> call(TableColumn.CellDataFeatures<RowModel, Rating> column) {
            return ((AgreementModel)column.getValue().getItem()).RKiProperty();
        }
    });
    RKiCol.setCellFactory(TextFieldTableCell.forTableColumn());
    customerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Customer>, ObservableValue<Customer>>() {
        @Override
        public ObservableValue<Customer> call(TableColumn.CellDataFeatures<RowModel, Customer> column) {
            return ((AgreementModel)column.getValue().getItem()).customerProperty();
        }
    });
    customerCol.setCellFactory(TextFieldTableCell.forTableColumn());
    employeeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Employee>, ObservableValue<Employee>>() {
        @Override
        public ObservableValue<Employee> call(TableColumn.CellDataFeatures<RowModel, Employee> column) {
            return ((AgreementModel)column.getValue().getItem()).employeeProperty();
        }
    });
    employeeCol.setCellFactory(TextFieldTableCell.forTableColumn());
    vehicleCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RowModel, Vehicle>, ObservableValue<Vehicle>>() {
        @Override
        public ObservableValue<Vehicle> call(TableColumn.CellDataFeatures<RowModel, Vehicle> column) {
            return ((AgreementModel)column.getValue().getItem()).vehicleProperty();
        }
    });

    }

@Override
public GuiTable getTable() {
    return this;
}
}

