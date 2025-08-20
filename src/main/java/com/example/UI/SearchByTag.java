package com.example.UI;

import com.example.quanlithuvien.Reader;

import java.io.IOException;

public class SearchByTag extends SingleLineQuery {
    public SearchByTag() {
        super("Search by book's tags");
    }

    public void setOnAction(Reader reader) {
        confirm.setOnAction(e-> {
            try {
                reader.findByTag(this.getText());
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
