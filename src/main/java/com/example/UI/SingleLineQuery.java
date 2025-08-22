package com.example.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class SingleLineQuery {
    private final Text nameOfQuery;
    private final VBox box;
    protected TextField Element;
    protected Button confirm;
    private Button cancel;
    private final Rectangle rect;

    public SingleLineQuery(String nameOfQuery) {
        this.box = new VBox();
        this.nameOfQuery = new Text(nameOfQuery);
        box.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        box.setBackground(Background.EMPTY);
        Element = new TextField();
        Element.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(Element, Priority.ALWAYS);
        confirm = new Button("Confirm");
        cancel = new Button("Cancel");
        rect = new Rectangle(); // Initialize with fill
        rect.setWidth(300);
        rect.setHeight(300);
        rect.setFill(Color.rgb(255, 255, 255));
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
    }

    public void render(StackPane stackPane) {
        stackPane.getChildren().add(rect);
        box.setAlignment(Pos.CENTER);
        HBox temp = new HBox(10, confirm, cancel);
        temp.setAlignment(Pos.CENTER);
        box.getChildren().addAll(nameOfQuery, Element, temp);
        StackPane.setAlignment(box, Pos.CENTER);
        confirm.setOnAction(e->close(stackPane));
        cancel.setOnAction(e -> close(stackPane));
        stackPane.getChildren().add(box);
        System.out.println("Rendered rectangle for " + nameOfQuery.getText() + ", Fill: " + rect.getFill());
    }

    public void resizeProperty(StackPane stackPane) {
        box.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.66));
        box.prefHeightProperty().bind(stackPane.heightProperty().divide(0.35));
        Element.prefWidthProperty().bind(box.widthProperty().multiply(0.4));
        confirm.prefWidthProperty().bind(box.widthProperty().multiply(0.3));
        cancel.prefWidthProperty().bind(box.widthProperty().multiply(0.3));
        rect.widthProperty().bind(stackPane.widthProperty().multiply(0.7));
        rect.heightProperty().bind(stackPane.heightProperty().multiply(0.4));
    }

    public void close(StackPane stackPane) {
        if (stackPane != null) {
            stackPane.getChildren().removeAll(rect, box);
        }
    }

    public String getText() {
        return Element.getText();
    }
}