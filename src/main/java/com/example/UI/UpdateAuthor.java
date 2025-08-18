package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

public class UpdateAuthor extends GeneralUpdate{
    public UpdateAuthor(Book book) {
        super("author");
        oldElement.setText(book.getAuthor());
        newElement.setText(book.getAuthor());
        newElement.setPromptText("Enter a new author");
    }

    @Override
    public void setUp(StackPane stackPane, Reader reader, Book book) {
        String newAuthor = newElement.getText();
        book.updateAuthor(newAuthor);
    }
}
