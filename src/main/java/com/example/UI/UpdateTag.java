package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class UpdateTag extends GeneralUpdate{
    public UpdateTag(Book book) {
        super("tags");
        oldElement.setText(book.getBookTag());
        newElement.setText(book.getBookTag());
        newElement.setPromptText("Enter a new tags");
    }

    @Override
    public void setUp(StackPane stackPane, Reader reader, Book book, Runnable runnable) {
        confirm.setOnAction(e-> {
            if (reader instanceof Librarian librarian) {
                try {
                    librarian.updateBookTag(book.getBookId(), newElement.getText());
                    runnable.run();
                    close(stackPane);
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
