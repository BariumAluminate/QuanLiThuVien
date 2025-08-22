package com.example.UI;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class Chooser {
    private final VBox mainBox;
    protected Button ID;
    protected Button title;
    protected Button author;
    protected Button tag;
    private final Rectangle rect;
    protected Button quit;

    public Chooser(String name) {
        mainBox = new VBox();
        mainBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Text nameOfQuery = new Text("Choose an element to " + name);
        nameOfQuery.setStyle("-fx-font-size: 30");

        ID = new Button("ID number");
        ID.setMaxWidth(Double.MAX_VALUE);
        ID.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(ID, Priority.ALWAYS);
        HBox.setHgrow(ID, Priority.ALWAYS);

        title = new Button("Book title");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(title, Priority.ALWAYS);
        HBox.setHgrow(title, Priority.ALWAYS);

        author = new Button("Author");
        author.setMaxWidth(Double.MAX_VALUE);
        author.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(author, Priority.ALWAYS);
        HBox.setHgrow(author, Priority.ALWAYS);

        tag = new Button("Tag");
        tag.setMaxWidth(Double.MAX_VALUE);
        tag.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(tag, Priority.ALWAYS);
        HBox.setHgrow(tag, Priority.ALWAYS);

        quit = new Button("Quit");
        quit.prefWidthProperty().bind(mainBox.widthProperty().divide(4));
        quit.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(quit, Priority.ALWAYS);

        Separator[] sep = new Separator[5];
        for(int i = 0; i < 5; i++) {
            sep[i] = new Separator(Orientation.HORIZONTAL);
            sep[i].setVisible(false);
            sep[i].setPrefHeight(5);
            sep[i].prefWidthProperty().bind(mainBox.widthProperty());
        }

        rect = new Rectangle();
        rect.setWidth(300);
        rect.setHeight(300);
        rect.setFill(Color.rgb(255, 255, 255)); // light blue with 50% opacity
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(20);
        rect.setArcHeight(20);

        mainBox.getChildren().addAll(nameOfQuery, sep[0]);
        mainBox.getChildren().addAll(ID, sep[1]);
        mainBox.getChildren().addAll(title, sep[2]);
        mainBox.getChildren().addAll(author, sep[3]);
        mainBox.getChildren().addAll(tag, sep[4]);
        mainBox.getChildren().add(quit);
        mainBox.setAlignment(Pos.CENTER);
    }

    public void render(StackPane stackPane) {
        StackPane.setAlignment(mainBox, Pos.CENTER);
        stackPane.getChildren().addAll(rect, mainBox);
        quit.setOnAction(e->close(stackPane));
    }

    public void close(StackPane stackPane) {
        if(stackPane != null) {
            stackPane.getChildren().removeAll(rect, mainBox);
        }
    }

    public void resize(StackPane stackPane) {
        mainBox.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.66));
        mainBox.prefHeightProperty().bind(stackPane.heightProperty().multiply(0.39));
        rect.widthProperty().bind(stackPane.widthProperty().multiply(0.8));
        rect.heightProperty().bind(stackPane.heightProperty().multiply(0.5));
    }

    public VBox getMainBox() {
        return mainBox;
    }
}
