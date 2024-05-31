package com.presentation.mvc.views.agreement;

import com.presentation.mvc.views.View;
import com.presentation.mvc.views.generalgui.NiceHBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
//karl

public class AgreementView extends VBox implements View {
    public AgreementView(){
        getStyleClass().add("Aftaler");
    }
    @Override
    public void addButtons(Button... buttons) {
        getChildren().addAll(new NiceHBox("leftContainer", buttons));
    }
}
