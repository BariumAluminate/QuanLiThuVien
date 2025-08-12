package com.example.UI;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {
    private final VBox userFace;
    private final Text name;
    private final Button SignOut;

    private final VBox functionBox;
    private final Button addButton;
    private final Button removeButton;
    private final Button searchButton;
    private final Button updateButton;
    private final Button showButton;

    private final HBox objectBox;
    private final Button bookButton;
    private final  Button userButton;

    UserInterface(String userName, Runnable onSignOut) {
        // User block
        userFace = new VBox();
        userFace.setStyle("-fx-background-color: White;");
        userFace.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        name = new Text(userName);
        name.setStyle("-fx-font-size: 30");
        userFace.getChildren().add(name);
        SignOut = new Button("Sign out");
        SignOut.setOnAction(e->onSignOut.run());
        SignOut.setStyle("-fx-font-size: 20");
        userFace.getChildren().add(SignOut);
        userFace.getStyleClass().add("UserBox");

        //Function Button block
        functionBox = new VBox();
        functionBox.setStyle("-fx-background-color: deepskyblue;");
        functionBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        addButton = new Button("Add");
        addButton.setMaxHeight(Double.MAX_VALUE);
        addButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(addButton, Priority.ALWAYS);
        VBox.setVgrow(addButton, Priority.ALWAYS);
        addButton.getStyleClass().add("functionButton");

        removeButton = new Button("Remove");
        removeButton.setMaxWidth(Double.MAX_VALUE);
        removeButton.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(removeButton, Priority.ALWAYS);
        VBox.setVgrow(removeButton, Priority.ALWAYS);
        removeButton.getStyleClass().add("functionButton");

        updateButton = new Button("Update");
        updateButton.setMaxHeight(Double.MAX_VALUE);
        updateButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(updateButton, Priority.ALWAYS);
        VBox.setVgrow(updateButton, Priority.ALWAYS);
        updateButton.getStyleClass().add("functionButton");

        searchButton = new Button("Search");
        searchButton.setMaxHeight(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchButton, Priority.ALWAYS);
        VBox.setVgrow(searchButton, Priority.ALWAYS);
        searchButton.getStyleClass().add("functionButton");

        showButton = new Button("Show");
        showButton.setMaxHeight(Double.MAX_VALUE);
        showButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(showButton, Priority.ALWAYS);
        VBox.setVgrow(showButton, Priority.ALWAYS);
        showButton.getStyleClass().add("functionButton");

        Separator sep1 = new Separator(Orientation.HORIZONTAL);
        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        Separator sep3 = new Separator(Orientation.HORIZONTAL);
        Separator sep4 = new Separator(Orientation.HORIZONTAL);

        functionBox.getChildren().addAll(addButton, sep1,removeButton, sep2);
        functionBox.getChildren().addAll(updateButton, sep3, searchButton, sep4, showButton);

        //Object to choose
        objectBox = new HBox();
        objectBox.setStyle("-fx-background-color: deepskyblue;");
        objectBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        bookButton = new Button("Book");
        bookButton.setMaxHeight(Double.MAX_VALUE);
        bookButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(bookButton, Priority.ALWAYS);
        VBox.setVgrow(bookButton, Priority.ALWAYS);

        userButton = new Button("User");
        userButton.setMaxHeight(Double.MAX_VALUE);
        userButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(userButton, Priority.ALWAYS);
        VBox.setVgrow(userButton, Priority.ALWAYS);

        Separator objSep = new Separator(Orientation.VERTICAL);

        objectBox.getChildren().addAll(bookButton, objSep,userButton);

        bookButton.getStyleClass().add("objectButton");
        userButton.getStyleClass().add("objectButton");
    }

    public void resizeUserFace(StackPane stackPane) {
        userFace.prefWidthProperty().bind(stackPane.widthProperty().divide(4));
        userFace.prefHeightProperty().bind(stackPane.heightProperty().divide(4));
    }

    public void resizeFunctionBox(StackPane stackPane) {
        functionBox.prefWidthProperty().bind(stackPane.widthProperty().divide(4));
        functionBox.prefHeightProperty().bind(stackPane.heightProperty().divide(4).multiply(3));
    }

    public void resizeObjectBox(StackPane stackPane) {
        objectBox.prefWidthProperty().bind(stackPane.widthProperty().divide(4).multiply(3));
        objectBox.prefHeightProperty().bind(stackPane.heightProperty().divide(8));
    }

    public void resizeAll(StackPane stackPane) {
        resizeUserFace(stackPane);
        resizeObjectBox(stackPane);
        resizeFunctionBox(stackPane);
    }

    public void render(StackPane stackPane) {
        StackPane.setAlignment(userFace, Pos.TOP_LEFT);
        stackPane.getChildren().addFirst(userFace);
        userFace.setAlignment(Pos.CENTER);

        StackPane.setAlignment(functionBox, Pos.BOTTOM_LEFT);
        stackPane.getChildren().add(functionBox);
        functionBox.setAlignment(Pos.CENTER);

        StackPane.setAlignment(objectBox, Pos.TOP_RIGHT);
        stackPane.getChildren().add(objectBox);
        objectBox.setAlignment(Pos.CENTER);
    }

    public void close(StackPane stackPane) {
        if (stackPane != null) {
            userFace.prefWidthProperty().unbind();
            userFace.prefHeightProperty().unbind();
            functionBox.prefWidthProperty().unbind();
            functionBox.prefHeightProperty().unbind();
            objectBox.prefWidthProperty().unbind();
            objectBox.prefHeightProperty().unbind();

            // Remove specific nodes instead of all VBox/HBox
            stackPane.getChildren().removeAll(userFace, functionBox, objectBox);
        }
    }

    public void removeQuery() {
        removeQuery remove = new removeQuery();
    }
}