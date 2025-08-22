package com.example.quanlithuvien;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Librarian extends Reader {
    public static final String BASE_URL = "http://20.196.64.166:8080";

    /**
     * Phương thức khởi tạo.
     *
     * @param stringId  string Id
     * @param password  mật khẩu
     * @param api_KEY   api_KEY
     * @param csrftoken csrftoken
     */
    public Librarian(String stringId, String name, String password, String api_KEY, String csrftoken) {
        super(stringId, name, password, api_KEY, csrftoken);
    }

    /**
     * Thêm sách vào thư viện.
     *
     * @param book sách được thêm
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void addBook(Book book) throws IOException, InterruptedException {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null!");
        }
        System.out.println(BookService.addBook(this, book));
    }

    /**
     * Xóa sách khỏi thư viện thông qua bookId.
     *
     * @param bookId Id của sách bị xóa
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void removeBook(String bookId) throws IOException, InterruptedException {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be be null or empty!");
        }
        System.out.println(BookService.removeBook(this, bookId));
    }

    /**
     * Tìm kiếm sách thông qua Id của nó.
     *
     * @param bookId Id của sách cần tìm
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void findBookById(String bookId) throws IOException, InterruptedException {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("BookId title cannot be be null or empty!");
        }
        System.out.println(BookService.findBookById(this, bookId));
    }

    /**
     * Tìm kiếm sách thông qua tên của nó.
     *
     * @param title Tên cuốn sách cần tìm
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void findBookByName(String title) throws IOException, InterruptedException {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be be null or empty!");
        }
        System.out.println(BookService.findBookByName(this, title));
    }


    /**
     * Thêm người dùng.
     *
     * @param stringId Id người dùng
     * @param name     Tên người dùng
     * @param password Mật khẩu
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void addUser(String stringId, String name, String password) throws IOException, InterruptedException {
        if (stringId == null || name == null || password == null) {
            throw new IllegalArgumentException("User details cannot be null");
        }
        Reader reader = new Reader(stringId, name, password);
        String response = UserService.addReader(getCsrftoken(), getStringId(), getApi_KEY(), reader);
        System.out.println(response);
    }

    /**
     * Cập nhật tên sách
     *
     * @param bookId Id của sách cần cập nhật
     * @param title  Tên cuốn sách
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void updateBookName(String bookId, String title) throws IOException, InterruptedException {
        if (bookId == null || title == null) {
            throw new IllegalArgumentException("Book ID or title cannot be null");
        }
        System.out.println(BookService.updateBookName(this, bookId, title));
    }

    /**
     * Cập nhật tác giả cuốn sách thông qua bookId.
     *
     * @param bookId Id của cuốn sách cần cập nhật
     * @param author Tác giả cúa cuốn sách
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void updateBookAuthor(String bookId, String author) throws IOException, InterruptedException {
        if (bookId == null || author == null) {
            throw new IllegalArgumentException("Book ID or author cannot be null");
        }
        System.out.println(BookService.updateAuthor(this, bookId, author));
    }

    /**
     * Cập nhật bookTag vào bookId.
     *
     * @param bookId  Id của sách cần cập nhật
     * @param bookTag tag dùng để cập nhật
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void addBookTag(String bookId, String bookTag) throws IOException, InterruptedException {
        if (bookId == null || bookTag == null) {
            throw new IllegalArgumentException("Book ID or tag cannot be null");
        }
        System.out.println(BookService.updateBookTag(this, bookId, bookTag));
    }

    /**
     * Xóa bookTag của cuốn sách.
     *
     * @param bookId Id của sách cần xóa
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void removeBookTag(String bookId) throws IOException, InterruptedException {
        if (bookId == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        System.out.println(BookService.updateBookTag(this, bookId, null));
    }

    /**
     * Cập nhật bookTag mới cho cuốn sách.
     *
     * @param bookId Id của sách cần cập nhật
     * @param newTag tag mới dùng để cập nhật
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void updateBookTag(String bookId, String newTag) throws IOException, InterruptedException {
        if (bookId == null || newTag == null) {
            throw new IllegalArgumentException("Book ID or new tag cannot be null");
        }
        System.out.println(BookService.updateBookTag(this, bookId, newTag));
    }

    /**
     * Tìm kiếm thông tin người dùng thông qua Id của họ.
     *
     * @param stringId Id người dùng
     * @throws IOException          ngoại lệ IO
     * @throws InterruptedException ngoại lệ Interrupted
     */
    public void findUser(String stringId) throws IOException, InterruptedException {
        if (stringId == null) {
            throw new IllegalArgumentException("stringId or new tag cannot be null");
        }
        Reader reader = new Reader(stringId);
        String response = UserService.showReaderInfo(reader);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
    }
}
