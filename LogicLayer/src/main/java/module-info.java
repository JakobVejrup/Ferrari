module com.logic {
    requires com.model;
    requires com.data;
    requires com.rki;
    exports com.logic.services.enums;
    exports com.logic.services.handlers;
    exports com.logic.services;
    exports com.logic;
    exports com.logic.validation;
}