package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

public class UpdateName extends GeneralUpdate{
    public UpdateName(Book book) {
        super("name");
        oldElement.setText(book.getTitle());
        newElement.setText(book.getTitle());
        newElement.setPromptText("Enter a new title");
    }

    @Override
    public void setUp(StackPane stackPane, Reader reader, Book book ) {
        confirm.setOnAction(e-> {
            String newName = newElement.getText();
            book.updateTitle(newName);
            close(stackPane);
        });
    }
}
