package com.example.UI;

import com.example.quanlithuvien.Book;
import javafx.scene.layout.StackPane;

public class UpdateName extends GeneralUpdate{

    public UpdateName(Book book) {
        super("name");
        oldElement.setText(book.getTitle());
        newElement.setText(book.getTitle());
        newElement.setPromptText("Enter a new title");
    }

    @Override
    public void setUp(StackPane stackPane) {

    }
}
