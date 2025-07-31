package com.example.springapi.api.book;

import java.util.List;

public class ListResponseBook {
    private List<Bookclass> books;
    private String message;

    public ListResponseBook(List<Bookclass> books, String message) {
        this.books = books;
        this.message = message;
    }

    public List<Bookclass> getBooks() {
        return books;
    }

    public void setBooks(List<Bookclass> books) {
        this.books = books;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
