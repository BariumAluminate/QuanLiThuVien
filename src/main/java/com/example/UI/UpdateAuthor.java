package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class UpdateAuthor extends GeneralUpdate{
    public UpdateAuthor(Book book) {
        super("author");
        oldElement.setText(book.getAuthor());
        newElement.setText(book.getAuthor());
        newElement.setPromptText("Enter a new author");
    }

    @Override
    public void setUp(StackPane stackPane, Reader reader, Book book, Runnable runnable) {
        confirm.setOnAction(_ -> {
            if (reader instanceof Librarian librarian) {
                try {
                    librarian.updateBookAuthor(book.getBookId(), newElement.getText());
                    runnable.run();
                    close(stackPane);
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
