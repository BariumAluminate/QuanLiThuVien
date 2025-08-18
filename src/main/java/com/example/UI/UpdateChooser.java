package com.example.UI;

import com.example.quanlithuvien.Book;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

public class UpdateChooser extends Chooser {
    public UpdateChooser() {
        super("update");
        this.getMainBox().getChildren().remove(ID);
    }

    public void updateName(StackPane stackPane, Reader user, Book book) {
        UpdateName name = new UpdateName(book);
        name.render(stackPane);
        name.resize(stackPane);
        name.setUp(stackPane, user, book);
    }

    public void updateAuthor(StackPane stackPane, Reader user, Book book) {
        UpdateAuthor updateAuthor = new UpdateAuthor(book);
        updateAuthor.render(stackPane);
        updateAuthor.resize(stackPane);
        updateAuthor.setUp(stackPane, user, book);
    }

    public void updateTags(StackPane stackPane, Reader user, Book book) {
        UpdateTag updateTag = new UpdateTag(book);
        updateTag.render(stackPane);
        updateTag.resize(stackPane);
        updateTag.setUp(stackPane, user, book);
    }

    public void setOnAction(StackPane stackPane, Reader user, Book book) {
        title.setOnAction(e->updateName(stackPane, user, book));
        author.setOnAction(e->updateAuthor(stackPane, user, book));
        tag.setOnAction(e->updateTags(stackPane, user, book));
    }
}
