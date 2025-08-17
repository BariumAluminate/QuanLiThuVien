package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import javafx.scene.layout.StackPane;

public class UpdateChooser extends Chooser {
    public UpdateChooser() {
        super("update");
    }

    public void updateID() {

    }

    public void updateName(StackPane stackPane, Librarian user, Book book) {
        UpdateName name = new UpdateName(book);
        name.render(stackPane);
    }

    public void updateAuthor() {

    }

    public void updateTags() {

    }

    public void setOnAction(StackPane stackPane, Librarian user, Book book) {

    }
}
