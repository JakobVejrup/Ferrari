package com.presentation.mvc.views.agreement;

import com.dlsc.formsfx.model.structure.DateField;
import com.presentation.mvc.models.agreements.AgreementModel;
import com.presentation.mvc.views.View;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.text.DecimalFormat;

public class OpenAgreementView extends VBox implements View{
    private HBox buttonBox;
    public OpenAgreementView(Pane employeeView, Pane customerView, Pane vehicleView, AgreementModel model, boolean open) {
        getStyleClass().add("rightContainer");
        DecimalFormat df = new DecimalFormat("#.00"); 
        TextField rki = new TextField(model.getRki() != null ? model.getRki().toString() : "");
        rki.setEditable(false);
        model.RKiProperty().addListener( (observable, old, newval) -> rki.setText(newval != null ? newval.toString() : ""));

        TextField startAgreement = new TextField(model.getStartAgreement().toString());
        startAgreement.setEditable(false);
        
        TextField rate = new TextField(df.format(model.getDaysRate()) + "%");
        rate.setEditable(false);
        model.daysRateProperty().addListener( (observable, old, newval) -> rate.setText(df.format(newval) + "%"));

        TextField totalRate = new TextField(df.format(model.getTotalRate()) + "%");
        totalRate.setEditable(false);
        model.totalRateProperty().addListener( (observable, old, newval) -> totalRate.setText(df.format(newval) + "%"));

        TextField totalAmount = new TextField(df.format(model.getEndPrice()) + ".Kr");
        totalAmount.setEditable(false);
        model.endPriceProperty().addListener( (observable, old, newval) -> totalAmount.setText(df.format(newval) + ".Kr"));

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
        StartValue.setEditable(open);

        TextField FixedTerms = new TextField(String.valueOf(model.getFixedTerms()));
        Bindings.bindBidirectional(FixedTerms.textProperty(), model.fixedTermsProperty(), new NumberStringConverter());
        FixedTerms.setEditable(open);

        getChildren().add(
            new HBox(
                customerView,
                employeeView,
                vehicleView,
                new VBox(
                    new NiceHBox("rightContainer", new Insets(5), new Label("Tilbud givet den: "), startAgreement),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Dagens rente: "), rate),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Rki:"), rki),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Total rente: "), totalRate),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Total Pris: "), totalAmount),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Start indskud:"), StartValue),
                    new NiceHBox("rightContainer", new Insets(5), new Label("Terminer:"), FixedTerms),
                    new NiceHBox("rightContainer", new Insets(5), new Label("i kraft dato: "), startDate),
                    new NiceHBox("rightContainer", new Insets(5), new Label("slut dato: "), endDate)
                ) 
            )
        );

    }
    @Override
    public void addButtons(Button... buttons) {
        if(buttonBox == null) {
            getChildren().add(buttonBox = new NiceHBox("buttonBar", buttons));
        }
        else
            buttonBox.getChildren().addAll(buttons);
    }
    

}
