package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

public class UpdateTag extends GeneralUpdate{
    public UpdateTag(Book book) {
        super("tags");
        oldElement.setText(book.getBookTag());
        newElement.setText(book.getBookTag());
        newElement.setPromptText("Enter a new tags");
    }

    @Override
    public void setUp(StackPane stackPane, Reader reader, Book book) {
        confirm.setOnAction(e-> {
            String newTags = newElement.getText();
            book.updateBookTag(newTags);
        });
    }
}
