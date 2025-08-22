package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class SearchByName extends SingleLineQuery {
    public SearchByName() {
        super("Search by book title");
    }

    public void setOnAction(StackPane stackPane, Reader reader, ObservableList<Book> bookList, Runnable runnable) {
        confirm.setOnAction(_ ->{
            try {
                String searchText = Element.getText();
                bookList.clear();
                ArrayList<Book> foundBooks = reader.findByName(searchText);
                 if (foundBooks != null) {
                     bookList.addAll(foundBooks);
                     runnable.run();
                     close(stackPane);
                 }
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        cancel.setOnAction(_ ->this.close(stackPane, reader, runnable, bookList));
    }

    public void close(StackPane stackPane, Reader user, Runnable runnable, ObservableList<Book> bookList) {
        close(stackPane);
        SearchChooser searchChooser = new SearchChooser();
        searchChooser.render(stackPane);
        searchChooser.resize(stackPane);
        searchChooser.setOnAction(stackPane, user, runnable, bookList);
    }
}
