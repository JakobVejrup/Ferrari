package com.presentation.tools.facade;

import com.model.entities.Employee;
import com.presentation.mvc.controllers.login.LoginController;
import com.presentation.mvc.controllers.modals.ModalController;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Facade is for the frontend, sets gui and such using facade pattern, i does a little mediatoring at logoff tho
public class Facade implements ModalSetup, ViewSetup, LoginManager{
    private static Facade instance;
    private BorderPane mainPane;
    private Stage mainStage;
    private ModalSetup modal;
    private LoginManager login;

    private Facade() {}
    public static Facade getInstance() {
        return instance == null ? instance = new Facade() : instance;
    }
    public void setLogin(LoginManager login) {
        this.login = login;
    }
    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    public void setModal(ModalSetup modal) {
        this.modal = modal;
    }
    @Override
    public void openModal(ModalController controller) {
        if(modal != null)
            modal.openModal(controller);
    }
    @Override
    public Object openModalResult(ModalController controller) {
        if(modal != null)
            return modal.openModalResult(controller);
        return null;
    }

    @Override
    public void setCenter(Node node) {
        if(mainPane != null)
            mainPane.setCenter(node);
    }

    @Override
    public void setTop(Node node) {
        if(mainPane != null)
            mainPane.setTop(new VBox(node));
    }

    @Override
    public void setLeft(Node node) {
        if(mainPane != null)
            mainPane.setLeft(node);
    }

    @Override
    public void setRight(Node node) {
        if(mainPane != null)
            mainPane.setRight(node);
    }
    @Override
    public void setBottom(Node node) {
        if(mainPane != null)
            mainPane.setBottom(node);
    }

    @Override
    public void login(Employee employee) {
        if (login != null)
            login.login(employee);
    }

    @Override
    public Employee getLoggedIn() {
        if (login != null)
            return login.getLoggedIn();
        return null;
    }
    // does a little mediating using the gui setters and the login 
    public void logOff() {
        setCenter(new LoginController().getView());
        setLeft(null);
        setRight(null);
        login(null);
    }
}
