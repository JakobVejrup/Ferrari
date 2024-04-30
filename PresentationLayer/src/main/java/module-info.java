module com.presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.model;
    requires com.logic;

    opens com.presentation to javafx.fxml;
    exports com.presentation;
}