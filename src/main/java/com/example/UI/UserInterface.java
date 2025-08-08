package com.example.UI;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {
    private final HBox userFace;
    private final Text name;

    private final VBox functionBox;
    private final Button addButton;
    private final Button removeButton;
    private final Button searchButton;
    private final Button updateButton;
    private final Button showButton;

    private final HBox objectBox;
    private final Button bookButton;
    private final  Button userButton;

    UserInterface(String userName) {
        //User block
        userFace = new HBox();
        userFace.setStyle("-fx-background-color: White;");
        userFace.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        name = new Text(userName);
        name.setStyle("-fx-font-size: 40");
        userFace.getChildren().add(name);

        //Function Button block
        functionBox = new VBox();
        functionBox.setStyle("-fx-background-color: blue;");
        functionBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        addButton = new Button("Add");
        addButton.setMaxHeight(Double.MAX_VALUE);
        addButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(addButton, Priority.ALWAYS);
        VBox.setVgrow(addButton, Priority.ALWAYS);
        removeButton = new Button("Remove");
        removeButton.setMaxWidth(Double.MAX_VALUE);
        removeButton.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(removeButton, Priority.ALWAYS);
        VBox.setVgrow(removeButton, Priority.ALWAYS);
        updateButton = new Button("Update");
        updateButton.setMaxHeight(Double.MAX_VALUE);
        updateButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(updateButton, Priority.ALWAYS);
        VBox.setVgrow(updateButton, Priority.ALWAYS);
        searchButton = new Button("Search");
        searchButton.setMaxHeight(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchButton, Priority.ALWAYS);
        VBox.setVgrow(searchButton, Priority.ALWAYS);
        showButton = new Button("Show");
        showButton.setMaxHeight(Double.MAX_VALUE);
        showButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(showButton, Priority.ALWAYS);
        VBox.setVgrow(showButton, Priority.ALWAYS);
        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        Separator sep4 = new Separator(Orientation.HORIZONTAL);
        functionBox.getChildren().addAll(addButton, sep1,removeButton, sep2);
        functionBox.getChildren().addAll(updateButton, sep3, searchButton, sep4, showButton);

        //Object to choose
        objectBox = new HBox();
        objectBox.setStyle("-fx-background-color: blue;");
        objectBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        bookButton = new Button("Book");
        userButton = new Button("User");
        objectBox.getChildren().addAll(bookButton, userButton);
    }

    public void resizeUserFace(StackPane stackPane) {
        userFace.prefWidthProperty().bind(stackPane.widthProperty().divide(4));
        userFace.prefHeightProperty().bind(stackPane.heightProperty().divide(4));
    }

    public void resizeFunctionBox(StackPane stackPane) {
        functionBox.prefWidthProperty().bind(stackPane.widthProperty().divide(4));
        functionBox.prefHeightProperty().bind(stackPane.heightProperty().divide(4).multiply(3));
    }

    public void render(StackPane stackPane) {
        StackPane.setAlignment(userFace, Pos.TOP_LEFT);
        stackPane.getChildren().addFirst(userFace);
        userFace.setAlignment(Pos.CENTER);
        StackPane.setAlignment(functionBox, Pos.BOTTOM_LEFT);
        stackPane.getChildren().add(functionBox);
        functionBox.setAlignment(Pos.CENTER);
    }
}
