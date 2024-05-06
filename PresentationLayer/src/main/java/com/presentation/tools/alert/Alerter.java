package com.presentation.tools.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerter {

    public static boolean confirmation(String title, String header, String content) {
        Alert alert = setup(new Alert(AlertType.CONFIRMATION), title, header, content);
        return alert.showAndWait().isPresent() && alert.getResult() == ButtonType.OK;
    }

    public static boolean confirmation(String title, String content) {
        Alert alert = setup(new Alert(AlertType.CONFIRMATION), title, content);
        return alert.showAndWait().isPresent() && alert.getResult() == ButtonType.OK;
    }

    public static void warning(String title, String header, String content) {
        Alert alert = setup(new Alert(AlertType.WARNING), title, header, content);
        alert.showAndWait();
    }

    public static void warning(String title, String content) {
        Alert alert = setup(new Alert(AlertType.WARNING), title, content);
        alert.showAndWait();
    }

    private static Alert setup(Alert alert, String title, String header, String content) {
        alert.setHeaderText(header);
        return finishAlert(alert, title, content);
    }
    private static Alert setup(Alert alert, String title, String content) {
        return finishAlert(alert, title, content);
    }

    private static Alert finishAlert(Alert alert, String title, String content) {
        alert.setTitle(title);
        alert.setContentText(content);
        return alert;
    }
}
