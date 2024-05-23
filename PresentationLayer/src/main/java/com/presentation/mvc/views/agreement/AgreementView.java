package com.presentation.mvc.views.agreement;
import com.presentation.mvc.views.View;
import com.presentation.mvc.views.table.ui.GuiTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AgreementView extends VBox implements View {
    private Button newAgreement;
    public AgreementView(EventHandler<ActionEvent>newAgreementAction){
        getStyleClass().add("Aftaler");
        newAgreement = new Button("Lav ny aftale");
        newAgreement.setOnAction(newAgreementAction);
        getChildren().add(newAgreement);
    }
    public void setTable(GuiTable table){
        table.setup(this);
    }
    @Override
    public void addButtons(Button... buttons) {
        getChildren().addAll(new HBox(buttons));
    }
    
}
