package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class GeneralUpdate {
    protected VBox mainBox;
    protected Text name;
    protected Text oldElement;
    protected TextField newElement;
    protected Button confirm;
    protected Button cancel;
    protected Rectangle rect;

    public GeneralUpdate(String nameOfUpdate) {
        name = new Text("Update " + nameOfUpdate);
        Label a = new Label("Old");
        Label b = new Label("New");

        oldElement = new Text();
        VBox old = new VBox(2, a, oldElement);
        old.setAlignment(Pos.CENTER);
        oldElement.setStyle("-fx-font-size: 30");

        newElement = new TextField();
        newElement.setStyle("-fx-font-size: 30");
        VBox newT = new VBox(2, b, newElement);
        newT.setAlignment(Pos.CENTER);
        newElement.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(newElement, Priority.ALWAYS);

        HBox changes = new HBox();
//        old.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//        newT.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//        changes.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        changes.setAlignment(Pos.CENTER);
        changes.setSpacing(20);

        // Let old and newT expand equally
        HBox.setHgrow(old, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(newT, javafx.scene.layout.Priority.ALWAYS);

        // Bind widths to half of changes
        old.prefWidthProperty().bind(changes.widthProperty().divide(2));
        newT.prefWidthProperty().bind(changes.widthProperty().divide(2));

        changes.getChildren().addAll(old, newT);

        confirm = new Button("Confirm");
        cancel = new Button("Cancel");
        HBox buttons = new HBox(2, confirm, cancel);
        HBox.setHgrow(confirm, Priority.ALWAYS);
        HBox.setHgrow(cancel, Priority.ALWAYS);

        // Bind width to half of buttons
        confirm.prefWidthProperty().bind(buttons.widthProperty().divide(2));
        cancel.prefWidthProperty().bind(buttons.widthProperty().divide(2));

        mainBox = new VBox();
        Separator c = new Separator(Orientation.HORIZONTAL);
        c.setVisible(false);
        c.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        mainBox.getChildren().addAll(name, changes, c, buttons);
        StackPane.setAlignment(name, Pos.TOP_CENTER);
        mainBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        mainBox.setAlignment(Pos.CENTER);

        rect = new Rectangle();
        rect.setWidth(300);
        rect.setHeight(300);
        rect.setFill(Color.rgb(255, 255, 255)); // light blue with 50% opacity
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
    }

    public void render(StackPane stackPane) {
        StackPane.setAlignment(mainBox, Pos.CENTER);
        stackPane.getChildren().addAll(rect, mainBox);
        cancel.setOnAction(e->close(stackPane));
    }

    public void resize(StackPane stackPane) {
        mainBox.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.66));
        mainBox.prefHeightProperty().bind(stackPane.heightProperty().multiply(0.39));
        rect.widthProperty().bind(stackPane.widthProperty().multiply(0.8));
        rect.heightProperty().bind(stackPane.heightProperty().multiply(0.5));
    }

    public void close(StackPane stackPane) {
        stackPane.getChildren().removeAll(rect, mainBox);
    }

    public abstract void setUp(StackPane stackPane, Reader reader, Book book, Runnable runnable);
}
