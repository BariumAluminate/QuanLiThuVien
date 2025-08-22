package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShowUserInfo {
    private final VBox mainBox;
    private final Rectangle rect;
    private final Button quit;

    public ShowUserInfo(Reader reader) {
        mainBox = new VBox();

        Label nameofQuery = new Label("Your information");
        nameofQuery.getStyleClass().add("Label");
        Label idLabel = new Label("ID: \t" + reader.getStringId());
        idLabel.getStyleClass().add("Label");
        Label nameLabel = new Label("Name: \t" + reader.getName());
        nameLabel.getStyleClass().add("Label");
        quit = new Button("Quit");

        mainBox.getChildren().addAll(nameofQuery, idLabel, nameLabel, quit);
        mainBox.setAlignment(Pos.CENTER);

        rect = new Rectangle(); // Initialize with fill
        rect.setWidth(300);
        rect.setHeight(300);
        rect.setFill(Color.rgb(255, 255, 255));
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
    }

    public void render(StackPane stackPane) {
        StackPane.setAlignment(mainBox, Pos.CENTER);
        stackPane.getChildren().addAll(rect, mainBox);
    }

    public void resize(StackPane stackPane) {
        mainBox.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.66));
        mainBox.prefHeightProperty().bind(stackPane.heightProperty().multiply(0.39));
        rect.widthProperty().bind(stackPane.widthProperty().multiply(0.8));
        rect.heightProperty().bind(stackPane.heightProperty().multiply(0.5));
        quit.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.2));
        quit.prefHeightProperty().bind(stackPane.heightProperty().multiply(0.08));
    }

    public void setup(StackPane stackPane) {
        quit.setOnAction(e->{
            if (stackPane != null) {
                stackPane.getChildren().removeAll(rect, mainBox);
            }
        });
    }
}
