package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class AddQuery {
    private final VBox mainBox;
    private final TextField id;
    private final TextField title;
    private final TextField author;
    private final TextField tag;
    protected Button confirm;
    private final Button cancel;
    Rectangle rect = new Rectangle();

    public AddQuery() {
        mainBox = new VBox();
        Text nameOfQuery = new Text("Add a book");
        Label idLabel = new Label("ID");
        id = new TextField();
        id.setPromptText("Enter the book's id number");
        Label titleLabel = new Label("Title");
        title = new TextField();
        title.setPromptText("Enter the title of the book");
        Label authorLabel = new Label("Author");
        author = new TextField();
        author.setPromptText("Enter the author of the book");
        Label tagLabel = new Label("Tags");
        tag = new TextField();
        tag.setPromptText("Enter all the tags of the book");
        confirm = new Button("Confirm");
        cancel = new Button("Cancel");

        mainBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        VBox idBox = new VBox();
        idBox.getChildren().addAll(idLabel,id);
        idBox.setAlignment(Pos.CENTER);
        idLabel.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(idLabel, Priority.ALWAYS);
        id.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(id, Priority.ALWAYS);

        VBox titleBox = new VBox(2, titleLabel, title);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(titleLabel, Priority.ALWAYS);
        title.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(title, Priority.ALWAYS);
        titleBox.setAlignment(Pos.CENTER);

        VBox authorBox = new VBox(2, authorLabel, author);
        authorBox.setAlignment(Pos.CENTER);
        authorLabel.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(authorLabel, Priority.ALWAYS);
        author.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(author, Priority.ALWAYS);

        VBox tagBox = new VBox(2, tagLabel, tag);
        tagBox.setAlignment(Pos.CENTER);
        tagLabel.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(tagLabel, Priority.ALWAYS);
        tag.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(tag, Priority.ALWAYS);

        HBox button = new HBox(2, confirm, cancel);
        button.setAlignment(Pos.CENTER);
        confirm.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(confirm, Priority.ALWAYS);
        cancel.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(cancel, Priority.ALWAYS);

        mainBox.getChildren().addAll(nameOfQuery, idBox, titleBox, authorBox, tagBox, button);
    }

    public void render(StackPane stackPane) {
        if (stackPane == null) {
            System.err.println("Error: StackPane is null");
            return;
        }
        cancel.setOnAction(e->close(stackPane));
        mainBox.setAlignment(Pos.CENTER);
        rect.setWidth(300);
        rect.setHeight(300);
        rect.setFill(Color.rgb(255, 255, 255)); // light blue with 50% opacity
        rect.setStroke(null);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        StackPane.setAlignment(mainBox, Pos.CENTER);
        stackPane.getChildren().addAll(rect, mainBox);
    }

    public void close(StackPane stackPane) {
        stackPane.getChildren().removeAll(rect ,mainBox);
    }

    public void resize(StackPane stackPane) {
        mainBox.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.66));
        mainBox.prefHeightProperty().bind(stackPane.heightProperty().divide(0.35));
        rect.widthProperty().bind(stackPane.widthProperty().multiply(0.7));
        rect.heightProperty().bind(stackPane.heightProperty().multiply(0.4));
    }

    public void setup(Reader reader) throws IOException, InterruptedException {
        String ID = this.id.getText();
        String name = this.title.getText();
        String author = this.author.getText();
        String tag = this.tag.getText();
        Book temp = new Book(ID, name, author, tag);
        if (reader instanceof Librarian librarian) {
            librarian.addBook(temp);
        }
    }

    public void setOnAction(StackPane stackPane, Reader reader) {
        confirm.setOnAction(e-> {
            try {
                setup(reader);
                close(stackPane);
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
