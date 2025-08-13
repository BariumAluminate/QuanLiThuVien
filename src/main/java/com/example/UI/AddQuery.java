package com.example.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddQuery {
    private Text nameOfQuery;
    private VBox mainBox;
    private Label idLabel;
    private TextField id;
    private Label titleLabel;
    private TextField title;
    private Label authorLabel;
    private TextField author;
    private Label tagLabel;
    private TextField tag;

    public AddQuery() {
        mainBox = new VBox();
        nameOfQuery = new Text("Add a book");
        idLabel = new Label("ID");
        id = new TextField("Enter the book's id number");
        titleLabel = new Label("Title");
        title = new TextField("Enter the title of the book");
        authorLabel = new Label("Author");
        author = new TextField("Enter the name of the author who write the book");
        tagLabel = new Label("Tags");
        tag = new TextField("Enter all the tags of the book");

        HBox idBox = new HBox();
        idBox.getChildren().addAll(idLabel,id);
        HBox titleBox = new HBox(2, titleLabel, title);
        HBox authorBox = new HBox(2, authorLabel, author);
        HBox tagBox = new HBox(2, tagLabel, tag);
        mainBox.getChildren().addAll(nameOfQuery, idBox, titleBox, authorBox, tagBox);
    }

    public void render(StackPane stackPane) {
        if (stackPane == null) {
            System.err.println("Error: StackPane is null");
            return;
        }
        StackPane.setAlignment(mainBox, Pos.CENTER);
        stackPane.getChildren().add(mainBox);
    }
}
