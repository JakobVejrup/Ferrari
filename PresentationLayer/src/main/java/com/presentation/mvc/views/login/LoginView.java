package com.presentation.mvc.views.login;

import com.presentation.mvc.models.employees.EmployeeModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {
    private TextField email;
    private PasswordField password;
    private EmployeeModel model;

    public LoginView(EmployeeModel model, EventHandler<ActionEvent> buttonAction) {
        this.model = model;
        setAlignment(Pos.TOP_CENTER);
        getStyleClass().add("login");
        email = new TextField();
        model.emailProperty().bind(email.textProperty());
        password = new PasswordField();
        model.passwordProperty().bind(password.textProperty());
        HBox texts = new HBox(new Label("Email:"), email, new Label("Password:"), password);
        Button login = new Button("Log ind");
        login.getStyleClass().add("acceptButton");
        login.setOnAction(buttonAction);
        getChildren().addAll(
                texts,
                login
        );
        setMargin(login, new Insets(10));
    }
}
