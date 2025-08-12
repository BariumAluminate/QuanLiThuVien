package com.example.quanlithuvien;

public class Librarian extends Reader {
    private BookService bookService;

    public Librarian(String stringId, String password, String api_KEY, String csrftoken) {
        super(stringId, password, api_KEY, csrftoken);
    }

    public void addBook(Book book) {
        bookService.addBook(this, book);
    }

    public void RemoveBook(String bookId) {
        bookService.removeBook(this, bookId);
    }

    public void findBookById(String bookId) {
        bookService.findBookById(this, bookId);
    }

    public void findBookByName(String title) {
        bookService.findBookById(this, title);
    }

    public void showAllBook() {
        bookService.showAllBook(this);
    }

    public void addUser(String stringId, String name, String password) {

    }

    public void updateBookName(String bookId, String title) {

    }

    public void updateBookAuthor(String bookId, String author) {

    }

    public void addBookTag(String bookId, String bookTag) {

    }

    public void removeBookTag(String bookId, String bookTag) {

    }

    public void updateBookTag(String bookId, String oldTag, String newTag) {

    }

    public void findUser(String stringId) {

    }
}
