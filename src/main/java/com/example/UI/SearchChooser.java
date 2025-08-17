package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

public class SearchChooser extends Chooser {
    public SearchChooser() {
        super("search");
    }

    public void findById(StackPane stackPane) {
        SearchByID idSearch = new SearchByID();
        idSearch.render(stackPane);
        idSearch.resizeProperty(stackPane);
    }

    public void findByName(StackPane stackPane) {
        SearchByName nameSearch = new SearchByName();
        nameSearch.render(stackPane);
        nameSearch.resizeProperty(stackPane);
    }

    public void findByAuthor(StackPane stackPane) {
        SearchByAuthor authorSearch = new SearchByAuthor();
        authorSearch.render(stackPane);
        authorSearch.resizeProperty(stackPane);
    }

    public void findByTag(StackPane stackPane) {
        SearchByTag tagSearch = new SearchByTag();
        tagSearch.render(stackPane);
        tagSearch.resizeProperty(stackPane);
    }

    public void setOnAction(StackPane stackPane, Reader user) {
        ID.setOnAction(e->findById(stackPane));
        title.setOnAction(e->findByName(stackPane));
        author.setOnAction(e->findByAuthor(stackPane));
        tag.setOnAction(e->findByTag(stackPane));
    }
}
