package com.example.UI;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class GeneralUpdate {
    protected HBox mainBox;
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
        newElement = new TextField();
        VBox changes = new VBox();
        changes.getChildren().addAll(oldElement, newElement);
        confirm = new Button("Confirm");
        cancel = new Button("Cancel");
        VBox buttons = new VBox(2, confirm, cancel);
        mainBox = new HBox();
        Separator c = new Separator(Orientation.HORIZONTAL);
        mainBox.getChildren().addAll(name, changes, a, buttons);
        mainBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        rect = new Rectangle();
        rect.setWidth(300);
        rect.setHeight(300);
        rect.setFill(Color.rgb(255, 255, 255));
        rect.setStroke(null);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
    }

    public abstract void setUp(StackPane stackPane);

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
}
