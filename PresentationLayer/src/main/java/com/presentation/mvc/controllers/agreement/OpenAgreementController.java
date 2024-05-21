package com.presentation.mvc.controllers.agreement;

import com.presentation.mvc.views.agreement.OpenAgreementView;
import com.presentation.mvc.controllers.Controller;
import javafx.scene.layout.Pane;
import com.presentation.mvc.models.agreements.OpenAgreementsModel;

public class OpenAgreementController implements Controller {
    private OpenAgreementView view;
    private OpenAgreementsModel model;
    
    public OpenAgreementController(OpenAgreementsModel model) {
        this.model = model;
        view = new OpenAgreementView(model);

    }

    @Override
    public Pane getView() {
        return view;
    }

}
