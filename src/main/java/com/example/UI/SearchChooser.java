package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

public class SearchChooser extends Chooser {
    public SearchChooser() {
        super("search");
        this.getMainBox().getChildren().remove(author);
    }

    public void findById(Reader reader, StackPane stackPane, Runnable runnable, ObservableList<Book> bookList) {
        SearchByID idSearch = new SearchByID();
        idSearch.render(stackPane);
        idSearch.resizeProperty(stackPane);
        idSearch.setOnAction(stackPane, reader, bookList, runnable);
    }

    public void findByName(Reader reader, StackPane stackPane, Runnable runnable, ObservableList<Book> bookList) {
        SearchByName nameSearch = new SearchByName();
        nameSearch.render(stackPane);
        nameSearch.resizeProperty(stackPane);
        nameSearch.setOnAction(stackPane, reader, bookList, runnable);

    }

    public void findByTag(Reader reader, StackPane stackPane, Runnable runnable, ObservableList<Book> bookList) {
        SearchByTag tagSearch = new SearchByTag();
        tagSearch.render(stackPane);
        tagSearch.resizeProperty(stackPane);
        tagSearch.setOnAction(stackPane, reader, bookList, runnable);
    }

    public void setOnAction(StackPane stackPane, Reader user, Runnable runnable, ObservableList<Book> bookList) {
        ID.setOnAction(_ ->{
            findById(user, stackPane, runnable, bookList);
            close(stackPane);
        });
        title.setOnAction(_ ->{
            findByName(user, stackPane, runnable, bookList);
            close(stackPane);
        });
        tag.setOnAction(_ ->{
            findByTag(user, stackPane, runnable, bookList);
            close(stackPane);
        });
    }
}
