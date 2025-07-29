package com.example.springapi.api.book;

public class Reponsebook {
    private Bookclass book;
    private String message;

    public Reponsebook(Bookclass book, String message) {
        this.book = book;
        this.message = message;
    }

    public Bookclass getBook() {
        return book;
    }

    public String getMessage() {
        return message;
    }

    public void setBook(Bookclass book) {
        this.book = book;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
