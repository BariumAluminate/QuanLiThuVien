package com.example.quanlithuvien;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.IOException;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String booktag;
    private String borrowerId;

    public Book() {

    }

    /**
     * Phương thức khởi tạo.
     */
    public Book(String bookId, String title, String author, String booktag) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.booktag = booktag;
        this.borrowerId = null;
    }

    /**
     * Phương thức khởi tạo.
     */
    public Book(String bookId, String title, String author, String booktag, String borrowerId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.booktag = booktag;
        this.borrowerId = borrowerId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookTag() {
        return booktag;
    }

    public String getBorrowedId() {
        return borrowerId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookTag(String booktag) {
        this.booktag = booktag;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateAuthor(String author) {
        this.author = author;
    }

    public void updatebooktag(String newTag) {
        this.booktag = newTag;
    }

    public void updateAll(String title, String author, String booktag) {
        this.author = author;
        this.title = title;
        this.booktag = booktag;
    }

    public StringProperty bookIdProperty() {
        return new SimpleStringProperty(bookId);  // Wrap the current String
    }

    public StringProperty bookTitle() {
        return new SimpleStringProperty(title);
    }

    public StringProperty bookAuthor() {
        return new SimpleStringProperty(author);
    }
}
