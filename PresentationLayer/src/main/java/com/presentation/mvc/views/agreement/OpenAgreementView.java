package com.presentation.mvc.views.agreement;

import com.model.entities.Agreement;
import com.model.entities.Vehicle;
import com.presentation.mvc.controllers.employee.EmployeesController;
import com.presentation.mvc.controllers.employee.SingleEmployeeController;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;
import com.presentation.mvc.views.employee.EmployeeView;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class OpenAgreementView extends HBox{
    private SingleEmployeeController employeeController;
    public OpenAgreementView(OpenAgreementsModel model) {

        employeeController = new SingleEmployeeController(model.getEmployee());

        TextField KundeNavn = new TextField(model.getCustomer().getName() );

        TextField Employee = new TextField(model.getEmployee().getName());

        TextField BilID = new TextField(model.getVehicle().getVehicleName());
        TextField BilPris = new TextField(String.valueOf(model.getVehicle().getPrice()));

        TextField Rki = new TextField(String.valueOf(model.getRki()));

        TextField StartValue = new TextField(String.valueOf(model.getStartValue()));
        Bindings.bindBidirectional(StartValue.textProperty(), model.startValueProperty(), new NumberStringConverter());

        TextField FixedTerms = new TextField(String.valueOf(model.getFixedTerms()));
        Bindings.bindBidirectional(FixedTerms.textProperty(), model.fixedTermsProperty(), new NumberStringConverter());

        getChildren().addAll(
                employeeController.getView(),
                new HBox(new Label("KundeNavn:"), KundeNavn),
                new HBox(new Label("Employee:"), Employee),
                new HBox(new Label("Rki:"), Rki),
                new HBox(new Label("StartValue:"), StartValue),
                new HBox(new Label("FixedTerms:"), FixedTerms)
        );

    }
    

}
