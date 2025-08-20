package com.example.UI;

import com.example.quanlithuvien.Reader;

import java.io.IOException;

public class SearchByID extends SingleLineQuery {
    public SearchByID() {
        super("Search by book's ID number");
    }

    public void setOnAction(Reader reader) {
        confirm.setOnAction(e-> {
            try {
                reader.findById(
                        this.getText()
                );

            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
