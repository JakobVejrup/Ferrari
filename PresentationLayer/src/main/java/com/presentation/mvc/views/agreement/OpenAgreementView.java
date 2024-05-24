package com.presentation.mvc.views.agreement;

import com.dlsc.formsfx.model.structure.DateField;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;
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

public class OpenAgreementView extends HBox implements View{
    public OpenAgreementView(Pane employeeView, Pane customerView, Pane vehicleView, OpenAgreementsModel model) {
        TextField rki = new TextField(model.getRki() != null ? model.getRki().toString() : "");
        rki.setEditable(false);

        model.RKiProperty().addListener( (observable, old, newval) -> rki.setText(newval != null ? newval.toString() : ""));

        TextField startAgreement = new TextField(model.getStartAgreement().toString());
        
        TextField rate = new TextField(String.valueOf(model.getDaysRate()));
        rate.setEditable(false);
        model.daysRateProperty().addListener( (observable, old, newval) -> rate.setText(newval.toString()));

        DatePicker startDate = new DatePicker(model.getStart() != null ? model.getStart().toLocalDate() : new Date(0).toLocalDate());
        model.startProperty().addListener( (observable, old, newval) -> startDate.setValue(newval != null ? newval.toLocalDate() : new Date(0).toLocalDate()));

        DatePicker endDate = new DatePicker(model.getEnd() != null ? model.getStartAgreement().toLocalDate() : new Date(0).toLocalDate());
        model.endProperty().addListener( (observable, old, newval) -> endDate.setValue(newval.toLocalDate()));

        TextField StartValue = new TextField(String.valueOf(model.getStartValue()));
        Bindings.bindBidirectional(StartValue.textProperty(), model.startValueProperty(), new NumberStringConverter());

        TextField FixedTerms = new TextField(String.valueOf(model.getFixedTerms()));
        Bindings.bindBidirectional(FixedTerms.textProperty(), model.fixedTermsProperty(), new NumberStringConverter());

        getChildren().addAll(
            employeeView,
            customerView,
            vehicleView,
            new VBox(
                new HBox(new Label("Tilbud givet den: "), startAgreement),
                new HBox(new Label("Dagens rente: "), rate),
                new HBox(new Label("Rki:"), rki),
                new HBox(new Label("StartValue:"), StartValue),
                new HBox(new Label("FixedTerms:"), FixedTerms),
                new HBox(new Label("i kraft dato: "), startDate),
                new HBox(new Label("slut dato: "), endDate)
            )
        );

    }
    @Override
    public void addButtons(Button... buttons) {
    }
    

}
