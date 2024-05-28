package com.presentation.mvc.controllers.agreement;

import com.presentation.mvc.views.agreement.OpenAgreementView;
import com.presentation.tools.FileMethods;
import com.presentation.tools.alert.Alerter;
import com.presentation.tools.facade.Facade;
import com.rki.rki.Rating;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import com.logic.ServiceSingleton;
import com.logic.handlers.Request;
import com.logic.math.LoanCalculator;
import com.logic.services.enums.CRUDType;
import com.logic.services.enums.ServiceType;
import com.model.entities.Agreement;
import com.model.entities.Customer;
import com.model.entities.Employee;
import com.model.entities.Invoice;
import com.model.entities.Vehicle;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.Controller;
import com.presentation.mvc.controllers.customers.SingleCustomerController;
import com.presentation.mvc.controllers.customers.modals.SelectCustomersController;
import com.presentation.mvc.controllers.employee.SingleEmployeeController;
import com.presentation.mvc.controllers.employee.modals.SelectEmployeeController;
import com.presentation.mvc.controllers.invoice.InvoicesInAgreementController;
import com.presentation.mvc.controllers.vehicle.SingleVehicleController;
import com.presentation.mvc.controllers.vehicle.modals.SelectVehicleController;
import com.presentation.mvc.models.Invoice.InvoiceModel;
import com.presentation.mvc.models.agreements.AgreementValidationModel;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.models.customer.CustomerModel;
import com.presentation.mvc.models.employees.EmployeeModel;
import com.presentation.mvc.models.table.RowModel;
import com.presentation.mvc.models.table.TableModel;
import com.presentation.mvc.models.vehicle.VehicleModel;

public class AgreementController extends Controller implements Consumer<AgreementValidationModel> {
    private OpenAgreementView view;
    private AgreementValidationModel model;
    private TableModel tableModel;
    private int signum;
    private SingleCustomerController customerController;
    private SingleEmployeeController employeeController;
    private SingleVehicleController vehicleController;
    private InvoicesInAgreementController invoicesController;
    private Button ready;
    private Button csvButton;
    public AgreementController(AgreementModel modelParam, boolean open) {
        signum = -1;
        if(modelParam.getStartAgreement() == null)
            modelParam.setStartAgreement(new Date(System.currentTimeMillis()));
        if(modelParam.getStart() == null)
            modelParam.setStart(new Date(System.currentTimeMillis()));
        if(modelParam.getEnd() == null)
            modelParam.setEnd(new Date(System.currentTimeMillis()));
        if(open)
            modelParam.setEmployee(Facade.getInstance().getLoggedIn());

        this.model = new AgreementValidationModel(modelParam, this);
        customerController = new SingleCustomerController(model.getCustomer());
        employeeController = new SingleEmployeeController(model.getEmployee());
        vehicleController = new SingleVehicleController(model.getVehicle());

        csvButton = new Button("Udskriv CSV-Fil");
        csvButton.setOnAction(this::csv);

        view = new OpenAgreementView(employeeController.getView(), customerController.getView(), vehicleController.getView(), model, open, csvButton);
        setView(view);
        
        if(open) {
            Button selectVehicle = new Button("Vælg bil");
            selectVehicle.setOnAction(this::chooseVehicle);
            vehicleController.addButtons(selectVehicle);
            Button selectCustomer = new Button("Vælg kunde");
            selectCustomer.setOnAction(this::chooseCustomer);
            customerController.addButtons(selectCustomer);        
            Button save = new Button("Gem aftale");
            save.setOnAction(this::save);
            ready = new Button("Udregn");
            ready.setOnAction(this::readyContinue);
            Button delete = new Button("Slet");
            delete.setOnAction(this::delete);
            view.addButtons(save, ready, delete);
        }
        if(open)
            tableModel = new TableModel(ServiceType.Invoice, new ArrayList<>());
        else
            tableModel = new TableModel(ServiceType.Invoice, InvoiceModel.makeModelsAsObjects(model.getPayments()));
        invoicesController = new InvoicesInAgreementController(tableModel);
        view.getChildren().add(invoicesController.getView());

        //guardclause is because the value will be updated, and it must set the boolean to false, to avoid a premature button clicking
        if (!open) 
            return;
        model.rkiBooleanProperty().set(false);
        accept(model);
        if(!((CustomerModel)model.getCustomer()).getEmpty())
            getRKiAndRate();
        
    }
    public void readyContinue(ActionEvent event) {
        if(signum == 0) {
            tableModel.removeAllRows();
            List<Invoice> invoices = new ArrayList<>();
            for(Invoice inv : LoanCalculator.låneBeregner(model))
                invoices.add(inv);
            tableModel.addAllRows(RowModel.makeRowModels(ServiceType.Invoice, InvoiceModel.makeModelsAsObjects(invoices)));
            model.setPayments(invoices);
        }
        else if(signum == 1) {
            if(model.getRki() == Rating.D && model.getVehicle().getPrice() - model.getStartValue() != 0) {
                Alerter.warning("Dårlig Rki kan ikke låne", "Kunden skal levere hele beløbet selv");
                return;
            }

            if(Facade.getInstance().getLoggedIn().getLoanLimit() > model.getVehicle().getPrice() - model.getStartValue() && Facade.getInstance().getLoggedIn().getOccupation() != Occupation.Manager)
                Alerter.warning("For stort beløb", "Gem aftalen, og en med større godkedelse beløb, kan godkende den");
            ServiceSingleton.getInstance().query(new Request(ServiceType.AgreementClosed, CRUDType.Create, model,
            (update) -> {
                if(update != null) 
                    Platform.runLater( () ->
                        Facade.getInstance().setCenter(new OpenAgreementsController().getView())
                    );
            }));
        }

    }
    public void chooseEmployee(ActionEvent event) {
        Object result = Facade.getInstance().openModalResult(new SelectEmployeeController());
        if(result != null) {
            employeeController.setModel((Employee)result);
            model.setEmployee((Employee)result);
        }
    }
    public void chooseVehicle(ActionEvent event) {
        Object result = Facade.getInstance().openModalResult(new SelectVehicleController());
        if(result != null) {
            vehicleController.setModel((Vehicle)result);
            model.setVehicle((Vehicle)result);
        }
    }
    public void chooseCustomer(ActionEvent event) {
        Object result = Facade.getInstance().openModalResult(new SelectCustomersController());
        if(result != null) {
            customerController.setModel((Customer)result);
            model.setCustomer((Customer)result);
            getRKiAndRate();
        }
    }

    public void delete(ActionEvent event) {
        ServiceSingleton.getInstance().query(new Request(ServiceType.AgreementOpen, CRUDType.Delete, model,
                (delete) -> {
                    if ((boolean) delete)
                        Platform.runLater(
                                () -> Facade.getInstance().setCenter(new OpenAgreementsController().getView()));
                }));
    }

    public void csv(ActionEvent event) {
        String fileName = model.getCustomer().getName() + "_" + model.getStart() + "_" + model.getVehicle().getName();
        FileMethods.makeCSV(fileName, model.getPayments(), (Stage)view.getScene().getWindow());
    }
    public void save(ActionEvent event) {
        if(model.getId() == 0) 
            ServiceSingleton.getInstance().query(new Request(ServiceType.AgreementOpen, CRUDType.Create, model, 
            (create) -> {
                if(create != null) 
                    Platform.runLater( () ->
                        Facade.getInstance().setCenter(new OpenAgreementsController().getView())
                    );
            }));
        else 
            ServiceSingleton.getInstance().query(new Request(ServiceType.AgreementOpen, CRUDType.Update, model,
            (update) -> {
                if(update != null) 
                    Platform.runLater( () ->
                        Facade.getInstance().setCenter(new OpenAgreementsController().getView())
                    );
            }));
    }
    @Override
    public void accept(AgreementValidationModel agreement) {
        if(agreement.fixedTermsBooleanProperty().get() && agreement.startValueBooleanProperty().get() && agreement.startBooleanProperty().get()
        && agreement.rkiBooleanProperty().get() && agreement.daysRateBooleanProperty().get()) {
            ready.setText("Udregn");   
            signum = 0;
        } 
        else {
            ready.setText("udfyld formel");   
            signum = -1;
        }

        if(agreement.invoicesBooleanProperty().get()) {
            ready.setText("Godkend");
            signum = 1;
        }
        ready.setDisable(signum == -1);
        csvButton.setDisable(signum != 1);
        Date newDate = new Date(agreement.getStart().getTime());
        newDate = Date.valueOf(newDate.toLocalDate().plusMonths(agreement.getFixedTerms()));
        agreement.setEnd(newDate);
    }
    private void getRKiAndRate() {
        ServiceSingleton.getInstance().query(new Request(ServiceType.Rate, CRUDType.Read, model, 
        (newAgreement) -> { 
            Platform.runLater( () -> {
                model.setRki(Rating.D);
                model.setRki(((Agreement)newAgreement).getRki());
                model.setDaysRate(0d);
                model.setDaysRate(((Agreement)newAgreement).getDaysRate());
            });
        }));
    }

}
