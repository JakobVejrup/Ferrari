module com.data {
    requires com.model;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires com.rki;
    exports com.data;
    exports com.data.interfaces;
    exports com.data.dao;


}