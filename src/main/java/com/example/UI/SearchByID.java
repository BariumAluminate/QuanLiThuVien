package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SearchByID extends SingleLineQuery {
    public SearchByID() {
        super("Search by book's ID number");
    }

    public void setOnAction(StackPane stackPane, Reader reader, ObservableList<Book> bookList, Runnable runnable) {
        confirm.setOnAction(e -> {
            try {
                String searchText = Element.getText();
                bookList.clear();
                Book foundBook = reader.findById(searchText);
                if (foundBook != null) {
                    bookList.add(foundBook);
                    runnable.run();
                    close(stackPane);
                }

            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        cancel.setOnAction(e->this.close(stackPane, reader, runnable, bookList));
    }

    public void close(StackPane stackPane, Reader user, Runnable runnable, ObservableList<Book> bookList) {
        this.close(stackPane);
        SearchChooser searchChooser = new SearchChooser();
        searchChooser.render(stackPane);
        searchChooser.resize(stackPane);
        searchChooser.setOnAction(stackPane, user, runnable, bookList);
    }
}