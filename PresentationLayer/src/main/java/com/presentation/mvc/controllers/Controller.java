package com.presentation.mvc.controllers;

import com.presentation.mvc.views.View;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
//anders
//not for polymorphic use just to reuse code
public class Controller {
    private Pane view;
    public void setView(Pane view) {
        this.view  = view;
    }
    public Pane getView() {
        return view;
    }
    public void addButtons(Button... buttons) {
        ((View) view).addButtons(buttons);
    }


}
