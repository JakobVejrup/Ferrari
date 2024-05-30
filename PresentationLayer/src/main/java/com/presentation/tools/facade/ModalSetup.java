package com.presentation.tools.facade;

import com.logic.handlers.Request;
import com.presentation.mvc.controllers.modals.ModalController;
//anders
public interface ModalSetup {
    public void openModal(ModalController controller);
    public Object openModalResult(ModalController controller);

}
