package com.presentation.tools.facade;

import com.presentation.App;
import com.presentation.tools.alert.Alerter;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ViewFactory{
    private BorderPane mainPane;
    public ViewFactory(BorderPane mainPane) {
        this.mainPane = mainPane;
    }
    public void setCenter(String scene) {
        try {
            mainPane.setCenter(changeView(scene, (Pane) mainPane.getCenter()));
        } catch (Exception e) {
            Alerter.warning("missing UI", "missing page", e.toString());
        }
    }
    public void setTop(String scene) {
        try {
            mainPane.setTop(changeView(scene, (Pane) mainPane.getTop()));
        }
        catch (IOException | RuntimeException e) {}
    }
    public void setLeft(String scene) {
        try {
            mainPane.setLeft(changeView(scene, (Pane) mainPane.getLeft()));
        } catch (IOException | RuntimeException e) {
        }
    }
    public void setRight(String scene) {
        try {
            mainPane.setRight(changeView(scene, (Pane) mainPane.getRight()));
        } catch (IOException | RuntimeException e) {
        }
    }
    // #endregion
    private Pane changeView(String scene, Pane location) throws IOException {
        Pane view = load(scene);
        if (location != null)
            mainPane.getChildren().remove(location);
        return view;
    }
    private Pane load(String scene) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(scene));
        if(loader.getController() == null)
            setController(loader, scene);
        return loader.load();
    }
    private Pane load(String scene, Object parameter) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(scene));
        if(loader.getController() == null)
            setController(loader, scene, parameter);
        return loader.load();
    }

    // might add dependencies to a controller, then add it to the loader, because
    // then the fxml ain't linked with a controller
    private void setController(FXMLLoader loader, String scene) {
        loader.setController( switch (scene) {

            default -> null;
        });
    }
    private void setController(FXMLLoader loader, String scene, Object parameter) {
        loader.setController(switch (scene) {

            default -> null;
        });
    }
}
