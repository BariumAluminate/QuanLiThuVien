package com.example.UI;

import com.example.quanlithuvien.Reader;

import java.io.IOException;

public class SearchByName extends SingleLineQuery {
    public SearchByName() {
        super("Search by book title");
    }

    public void setOnAction(Reader reader) {
        confirm.setOnAction(e->{
            try {
                reader.findByName(this.getText());
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
