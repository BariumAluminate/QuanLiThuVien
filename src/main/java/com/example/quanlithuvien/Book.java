package com.example.quanlithuvien;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String bookTag;
    private String BorrowerID;

    public Book() {

    }

    /**
     * Phương thức khởi tạo.
     */
    public Book(String bookId, String title, String author, String bookTag) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.bookTag = bookTag;
        this.BorrowerID = null;
    }

    /**
     * Phương thức khởi tạo.
     */
    public Book(String bookId, String title, String author, String bookTag, String borrowerID) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.bookTag = bookTag;
        BorrowerID = borrowerID;
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
        return bookTag;
    }

    public String getBorrowedId() {
        return BorrowerID;
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

    public void setBookTag(String bookTag) {
        this.bookTag = bookTag;
    }

    public void setBorrowerID(String borrowerID) {
        BorrowerID = borrowerID;
    }

    public void updateTitle(String title) {
        this.title = title;
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

    public void updateAll(String title, String author, String bookTag) {
        this.author = author;
        this.title = title;
        this.bookTag = bookTag;
    }

    public void returned() {

    }
}
