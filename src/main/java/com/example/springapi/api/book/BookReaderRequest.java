package com.example.springapi.api.book;

import com.example.springapi.api.reader.basicclass.Reader;

public class BookReaderRequest {
    private Bookclass book;
    private Reader reader;

    // Getters và Setters
    public Bookclass getBook() {
        return book;
    }
    public void setBook(Bookclass book) {
        this.book = book;
    }
    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
