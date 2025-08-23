package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class UpdateName extends GeneralUpdate{
    public UpdateName(Book book) {
        super("name");
        oldElement.setText(book.getTitle());
        newElement.setText(book.getTitle());
        newElement.setPromptText("Enter a new title");
    }

    @Override
    public void setUp(StackPane stackPane, Reader reader, Book book, Runnable runnable) {
        confirm.setOnAction(_ -> {
            if (reader instanceof Librarian librarian) {
                try {
                    librarian.updateBookName(book.getBookId(), newElement.getText());
                    runnable.run();
                    close(stackPane);
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
