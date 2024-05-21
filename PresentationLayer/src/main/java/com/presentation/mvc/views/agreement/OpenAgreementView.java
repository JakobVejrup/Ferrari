package com.presentation.mvc.views.agreement;

import com.presentation.mvc.models.agreements.OpenAgreementsModel;
import com.presentation.mvc.views.View;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class OpenAgreementView extends HBox implements View{
    public OpenAgreementView(Pane employeeView, Pane customerView, Pane vehicleView, OpenAgreementsModel model) {
        TextField Rki = new TextField(String.valueOf(model.getRki()));
        TextField StartValue = new TextField(String.valueOf(model.getStartValue()));
        Bindings.bindBidirectional(StartValue.textProperty(), model.startValueProperty(), new NumberStringConverter());

        TextField FixedTerms = new TextField(String.valueOf(model.getFixedTerms()));
        Bindings.bindBidirectional(FixedTerms.textProperty(), model.fixedTermsProperty(), new NumberStringConverter());

        getChildren().addAll(
            employeeView,
            customerView,
            vehicleView,
            new VBox(
                new HBox(new Label("Rki:"), Rki),
                new HBox(new Label("StartValue:"), StartValue),
                new HBox(new Label("FixedTerms:"), FixedTerms)
            )
        );

    }
    @Override
    public void addButtons(Button... buttons) {
    }
    

}
