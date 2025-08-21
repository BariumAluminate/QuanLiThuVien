package com.example.quanlithuvien;

import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    private String stringId;
    private String name;
    protected String password;
    protected String api_KEY;
    protected String csrftoken;

    /**
     * Phương thức khởi tạo.
     *
     * @param stringId  Id của người đọc
     * @param password  Mật khẩu
     * @param csrftoken csrftoken
     * @param api_KEY   api_KEY
     */
    public Reader(String stringId, String name, String password, String api_KEY, String csrftoken) {
        this.stringId = stringId;
        this.name = name;
        this.password = password;
        this.api_KEY = api_KEY;
        this.csrftoken = csrftoken;
    }

    /**
     * Phương thức khởi tạo.
     *
     * @param stringId Id người đọc
     * @param name     tên người dùng
     * @param password mật khẩu
     */
    public Reader(String stringId, String name, String password) {
        this.stringId = stringId;
        this.name = name;
        this.password = password;
    }

    /**
     * Phương thức khởi tạo.
     *
     * @param stringId Id của người đọc
     */
    public Reader(String stringId) {
        this.stringId = stringId;
    }

    public void setApi_KEY(String api_KEY) {
        this.api_KEY = api_KEY;
    }

    public void setCsrftoken(String csrftoken) {
        this.csrftoken = csrftoken;
    }

    public String getName() {
        return name;
    }

    public String getStringId() {
        return stringId;
    }

    public String getApi_KEY() {
        return api_KEY;
    }

    public String getCsrftoken() {
        return csrftoken;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Thông tin cuốn sách được mượn thông qua bookId.
     *
     * @param bookId id của cuốn sách được mượn
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void borrow(String bookId) throws IOException, InterruptedException {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId cannot be null!");
        }
        System.out.println(BookService.borrowBook(this, bookId));
    }

    /**
     * Tìm cuốn sách được mượn bằng Id của nó.
     *
     * @param bookId Id của cuốn sách được mượn
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void findById(String bookId) throws IOException, InterruptedException {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId cannot be null!");
        }
        System.out.println(BookService.findBookById(this, bookId));
    }

    /**
     * Tìm cuốn sách thông qua tên của nó.
     *
     * @param bookName tên của cuốn sách
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void findByName(String bookName) throws IOException, InterruptedException {
        if (bookName == null) {
            throw new IllegalArgumentException("bookName cannot be null!");
        }
        System.out.println(BookService.findBookByName(this, bookName));
    }

    /**
     * Tìm cuốn sách thông qua tag của nó.
     *
     * @param tag tag của cuốn sách
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void findByTag(String tag) throws IOException, InterruptedException {
        if (tag == null) {
            throw new IllegalArgumentException("tag cannot be null!");
        }
        System.out.println(BookService.findBookByBookTag(this, tag));
    }

    public ArrayList<Book> showAllBook() throws IOException, InterruptedException {
        return BookService.showAllBook(this);
    }
}
