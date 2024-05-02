module com.model {
    requires com.rki;
    requires java.sql;
    exports com.model.entities;
    exports com.model.threads;
    exports com.model.enums;

}