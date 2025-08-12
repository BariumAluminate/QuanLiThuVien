package com.example.quanlithuvien;

public class LibraryData {
    private Reader reader;
    private Book book;

    public LibraryData() {

    }

    public LibraryData(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
