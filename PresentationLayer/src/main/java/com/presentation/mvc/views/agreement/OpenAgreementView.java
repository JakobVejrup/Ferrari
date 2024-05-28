package com.presentation.mvc.views.agreement;

import com.dlsc.formsfx.model.structure.DateField;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.views.View;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import java.sql.Date;

public class OpenAgreementView extends VBox implements View{
    public OpenAgreementView(Pane employeeView, Pane customerView, Pane vehicleView, AgreementModel model, boolean open, Button csv) {
        TextField rki = new TextField(model.getRki() != null ? model.getRki().toString() : "");
        rki.setEditable(false);
        model.RKiProperty().addListener( (observable, old, newval) -> rki.setText(newval != null ? newval.toString() : ""));

        TextField startAgreement = new TextField(model.getStartAgreement().toString());
        startAgreement.setEditable(false);
        
        TextField rate = new TextField(String.valueOf(model.getDaysRate()));
        rate.setEditable(false);
        model.daysRateProperty().addListener( (observable, old, newval) -> rate.setText(newval.toString()));

        TextField totalRate = new TextField(String.valueOf(model.getTotalRate()));
        totalRate.setEditable(false);
        model.totalRateProperty().addListener( (observable, old, newval) -> totalRate.setText(newval.toString()));

        TextField totalAmount = new TextField(String.valueOf(model.getEndPrice()));
        totalAmount.setEditable(false);
        model.endPriceProperty().addListener( (observable, old, newval) -> totalAmount.setText(newval.toString()));

        DatePicker startDate = new DatePicker(model.getStart() != null ? model.getStart().toLocalDate() : new Date(0).toLocalDate());
        startDate.valueProperty().addListener( (observable, old, newval) -> model.setStart(newval != null ? Date.valueOf(newval) : new Date(System.currentTimeMillis())));
        model.startProperty().addListener( (observable, old, newval) -> startDate.setValue(newval != null ? newval.toLocalDate() : new Date(0).toLocalDate()));
        startDate.setEditable(open);
        startDate.setDisable(!open);

        DatePicker endDate = new DatePicker(model.getEnd() != null ? model.getStartAgreement().toLocalDate() : new Date(0).toLocalDate());
        model.endProperty().addListener( (observable, old, newval) -> endDate.setValue(newval.toLocalDate()));
        endDate.setEditable(false);
        endDate.setDisable(true);

        TextField StartValue = new TextField(String.valueOf(model.getStartValue()));
        Bindings.bindBidirectional(StartValue.textProperty(), model.startValueProperty(), new NumberStringConverter());
        totalAmount.setEditable(open);

        TextField FixedTerms = new TextField(String.valueOf(model.getFixedTerms()));
        Bindings.bindBidirectional(FixedTerms.textProperty(), model.fixedTermsProperty(), new NumberStringConverter());
        totalAmount.setEditable(open);
        getChildren().add(
            new HBox(
                employeeView,
                customerView,
                vehicleView,
                new VBox(
                    new HBox(new Label("Tilbud givet den: "), startAgreement),
                    new HBox(new Label("Dagens rente: "), rate),
                    new HBox(new Label("Rki:"), rki),
                    new HBox(new Label("Total rente: "), totalRate),
                    new HBox(new Label("Total Pris: "), totalAmount),
                    new HBox(new Label("Start indskud:"), StartValue),
                    new HBox(new Label("Terminer:"), FixedTerms),
                    new HBox(new Label("i kraft dato: "), startDate),
                    new HBox(new Label("slut dato: "), endDate)
                ),
                new VBox(csv)   
            )
        );

    }
    @Override
    public void addButtons(Button... buttons) {
        getChildren().add(new HBox(buttons));
    }
    

}
