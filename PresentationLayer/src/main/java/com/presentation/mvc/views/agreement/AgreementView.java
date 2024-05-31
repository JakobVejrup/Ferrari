package com.presentation.mvc.views.agreement;
import com.presentation.mvc.views.View;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.presentation.mvc.views.generalgui.NiceHBox;
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
