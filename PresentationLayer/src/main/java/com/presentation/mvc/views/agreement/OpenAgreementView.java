package com.presentation.mvc.views.agreement;

import com.model.entities.Agreement;
import com.model.entities.Vehicle;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class OpenAgreementView {
    public OpenAgreementView(OpenAgreementsModel model) {
        TextField KundeNavn = new TextField(model.getCustomer().getName());

        TextField Employee = new TextField(model.getEmployee().getName());

        TextField BilID = new TextField(model.getVehicle().getVehicleName());
        model.getVehicleNameProperty().bind(VehicleName.textProperty());

        TextField BilPris = new TextField(String.valueOf(model.getVehicle().getPrice()));
        model.getVehiclePriceProperty().bind(VehiclePrice.textProperty());

        TextField Rki = new TextField(String.valueOf(model.getRki()));
        model.RKiProperty().bind(Rki.textProperty());

        TextField StartValue = new TextField(String.valueOf(model.getstartvalue()));
        model.startValueProperty().bind(StartValue.textProperty());

        TextField FixedTerms = new TextField(String.valueOf(model.getfixedTerms()));
        model.fixedTermsProperty().bind(FixedTerms.textProperty());

        getChildren().addAll(
                new HBox(new Label("KundeNavn:"), KundeNavn),
                new HBox(new Label("Employee:"), Employee),
                new HBox(new Label("BilNavn:"), VehicleName),
                new HBox(new Label("BilPris:"), VehiclePrice),
                new HBox(new Label("Rki:"), Rki),
                new HBox(new Label("StartValue:"), StartValue),
                new HBox(new Label("FixedTerms:"), FixedTerms)
        );

    }
    

}
