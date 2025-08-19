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
    private VBox mainBox;
    private Rectangle rect;
    private Button quit;



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
