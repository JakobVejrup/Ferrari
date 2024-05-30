package com.presentation.mvc.controllers.leftnavbar;

import com.presentation.mvc.models.leftnavbar.NavButtonModel;
import com.presentation.mvc.views.leftnavbar.NavButtonView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
//anders
public class NavButtonController {
    private NavButtonView view;
    private NavButtonModel model;

    public NavButtonController(NavButtonModel model, EventHandler<MouseEvent> buttonAction) {
        this.model = model;
        view = new NavButtonView(model, buttonAction);
    }

    public NavButtonView getView() {
        return view;
    }
}
