package com.example.UI;

import com.example.quanlithuvien.Librarian;
import com.example.quanlithuvien.Reader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class removeQuery extends  SingleLineQuery{
    public removeQuery() {
        super("Remove");
    }

    public void setOnAction(StackPane stackPane, Reader user, Runnable runnable) {
        confirm.setOnAction(e-> {
            try {
                if (user instanceof Librarian librarian) {
                    librarian.removeBook(Element.getText());
                    close(stackPane);
                    runnable.run();
                }
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
