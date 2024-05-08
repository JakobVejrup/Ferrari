package com.presentation.mvc.models.login;

import com.presentation.mvc.views.login.LoginModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {

    public LoginView(LoginModel model, EventHandler<ActionEvent> buttonAction) {
        TextField email = new TextField();
        model.emailProperty().bind(email.textProperty());
        TextField password = new TextField();
        model.passwordProperty().bind(password.textProperty());
        HBox texts = new HBox(new Label("Email:"), email, new Label("Password:"), password);
        Button login = new Button("Log ind");
        login.setOnAction(buttonAction);
        getChildren().addAll(
                texts,
                login
        );
    }
}
