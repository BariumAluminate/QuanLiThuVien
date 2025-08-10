package com.example.quanlithuvien;

public class Librarian extends Reader {
    public Librarian(String stringId, String password, String api_KEY, String csrftoken) {
        super(stringId, password, api_KEY, csrftoken);
    }

    public void addBook(String stringId, String title, String author) {

    }

    public void RemoveBook(String bookId) {

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
