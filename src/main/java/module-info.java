module com.example.UI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires gson;
    requires java.net.http;
    requires java.sql;
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;
    requires javafx.base;

    opens com.example.UI to javafx.fxml, gson;
    exports com.example.UI;
    exports com.example.quanlithuvien;
    opens com.example.quanlithuvien to gson,javafx.fxml;
}