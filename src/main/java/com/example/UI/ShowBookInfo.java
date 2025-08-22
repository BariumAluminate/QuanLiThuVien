package com.example.UI;

import com.example.quanlithuvien.Book;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShowBookInfo {
    private final VBox mainBox;
    private final Rectangle rect;
    private final Button quit;

    public ShowBookInfo(Book book) {
        Label id = new Label(book.getBookId());
        id.getStyleClass().add("Label");
        Label name = new Label(book.getTitle());
        name.getStyleClass().add("Label");
        Label author = new Label(book.getAuthor());
        author.getStyleClass().add("Label");
        Label tags = new Label(book.getBookTag());
        tags.getStyleClass().add("Label");
        Label borrowedID = new Label(book.getBorrowedId());
        borrowedID.getStyleClass().add("Label");
        System.out.println(book.getBorrowedId());

        Label idLabel = new Label("ID");
        idLabel.getStyleClass().add("Label");
        Label nameLabel = new Label("Title");
        nameLabel.getStyleClass().add("Label");
        Label authorLabel = new Label("Author");
        authorLabel.getStyleClass().add("Label");
        Label tagsLabel = new Label("Tags");
        tagsLabel.getStyleClass().add("Label");
        Label borrowerLabel = new Label("Borrowed ID");
        borrowerLabel.getStyleClass().add("Label");
        Label nameofQuery = new Label("Show Book information");
        nameofQuery.getStyleClass().add("Label");

        VBox label = new VBox();
        label.getChildren().addAll(idLabel, nameLabel, authorLabel, tagsLabel, borrowerLabel);
        label.setAlignment(Pos.CENTER);

        VBox info = new VBox();
        info.getChildren().addAll(id, name, author, tags, borrowedID);
        info.setAlignment(Pos.CENTER);

        HBox submain = new HBox();
        submain.getChildren().addAll(label, info);
        submain.setAlignment(Pos.CENTER);
        submain.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        label.prefWidthProperty().bind(submain.widthProperty().divide(3));
        info.prefWidthProperty().bind(submain.widthProperty().divide(3).multiply(2));

        quit = new Button("Quit");

        mainBox = new VBox();
        mainBox.getChildren().addAll(nameofQuery, submain, quit);
        mainBox.setAlignment(Pos.CENTER);
        submain.prefWidthProperty().bind(mainBox.widthProperty().multiply(0.8));

        rect = new Rectangle(); // Initialize with fill
        rect.setWidth(300);
        rect.setHeight(300);
        rect.setFill(Color.rgb(255, 255, 255));
        rect.setStroke(null);
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
