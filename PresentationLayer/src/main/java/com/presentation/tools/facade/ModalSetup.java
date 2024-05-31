package com.presentation.tools.facade;

import com.presentation.mvc.controllers.modals.ModalController;
//anders 
//wants a modalcontroller to open a modal view
public interface ModalSetup {
    public void openModal(ModalController controller);
    public Object openModalResult(ModalController controller);

}
