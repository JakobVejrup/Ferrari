package com.presentation.tools.facade;

import javafx.scene.Node;

//methods signatures fit with borderpanes main actions
public interface ViewSetup {
    public void setCenter(Node node);
    public void setTop(Node node);
    public void setLeft(Node node);
    public void setRight(Node node);
    public void setBottom(Node node);

}
