package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

public class UpdateChooser extends Chooser {
    public UpdateChooser() {
        super("update");
        this.getMainBox().getChildren().remove(ID);
    }

    public void updateName(StackPane stackPane, Reader user, Book book, Runnable runnable) {
        UpdateName name = new UpdateName(book);
        name.render(stackPane);
        name.resize(stackPane);
        name.setUp(stackPane, user, book, runnable);
    }

    public void updateAuthor(StackPane stackPane, Reader user, Book book, Runnable runnable) {
        UpdateAuthor updateAuthor = new UpdateAuthor(book);
        updateAuthor.render(stackPane);
        updateAuthor.resize(stackPane);
        updateAuthor.setUp(stackPane, user, book, runnable);
    }

    public void updateTags(StackPane stackPane, Reader user, Book book, Runnable runnable) {
        UpdateTag updateTag = new UpdateTag(book);
        updateTag.render(stackPane);
        updateTag.resize(stackPane);
        updateTag.setUp(stackPane, user, book, runnable);
    }

    public void setOnAction(StackPane stackPane, Reader user, Book book, Runnable runnable) {
        title.setOnAction(_ ->updateName(stackPane, user, book, runnable));
        author.setOnAction(_ ->updateAuthor(stackPane, user, book, runnable));
        tag.setOnAction(_ ->updateTags(stackPane, user, book, runnable));
    }
}
