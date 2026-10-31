module edu.jbussiere.bdeb.a10 {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.checkerframework.checker.qual;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    exports edu.jbussiere.bdeb.a10;
    exports edu.jbussiere.bdeb.a10.view;
    exports edu.jbussiere.bdeb.a10.model;
    exports edu.jbussiere.bdeb.a10.control;
    exports edu.jbussiere.bdeb.a10.persistence.factory;
    exports edu.jbussiere.bdeb.a10.dao;
    exports edu.jbussiere.bdeb.a10.persistence.sql;
    exports edu.jbussiere.bdeb.a10.persistence.jdbc;
    exports edu.jbussiere.bdeb.a10.persistence.mock;

    opens edu.jbussiere.bdeb.a10.model to org.hibernate.orm.core;

}