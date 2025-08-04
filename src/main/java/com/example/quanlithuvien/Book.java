package com.example.quanlithuvien;

public class Book extends Item {
    private String author;
    private String bookTag;
    private String borrowedId;

    public Book() {

    }

    public Book(String id, String name, String borrowedId) {
        super(id, name);
        this.borrowedId = borrowedId;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateAuthor(String author) {
        this.author = author;
    }

    public void addBookTag(String tag) {

    }

    public void removeBookTag(String tag) {

    }

    public void updateBookTag(String newTag) {
        this.bookTag = newTag;
    }

    public void updateAll(String name, String author, String bookTag) {
        this.author = author;
        this.name = name;
        this.bookTag = bookTag;
    }

    public void show() {

    }

    public void returned() {

    }
}
