package com.example.springapi.api.book;

public class Bookclass {
    private String bookId;
    private String title;
    private String author;
    private String booktag;
    private String BorrowerId;


    //constructor
    public Bookclass(String bookId, String title, String author, String booktag, String borrowerId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.booktag = booktag;
        BorrowerId = borrowerId;
    }

    // Getters and Setters
    public String getbookId() {
        return bookId;
    }
    public void setbookId(String bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getBooktag() {
        return booktag;
    }
    public void setBooktag(String booktag) {
        this.booktag = booktag;
    }
    public String getBorrowerId() {
        return BorrowerId;
    }
    public void setBorrowerId(String borrowerId) {
        BorrowerId = borrowerId;
    }

}
