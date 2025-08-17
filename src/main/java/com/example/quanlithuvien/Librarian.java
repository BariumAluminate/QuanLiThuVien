package com.example.quanlithuvien;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Librarian extends Reader {
    public static final String BASE_URL = "http://20.196.64.166:8080";
    private final BookService bookService = new BookService();
    private final UserService userService = new UserService();

    /**
     * Phương thức khởi tạo.
     * @param stringId string Id
     * @param password mật khẩu
     * @param api_KEY api_KEY
     * @param csrftoken csrftoken
     */
    public Librarian(String stringId, String password, String api_KEY, String csrftoken) {
        super(stringId, password, api_KEY, csrftoken);
    }

    public void addBook(Book book) throws IOException, InterruptedException {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null!");
        }
        System.out.println(bookService.addBook(this, book));
    }

    public void RemoveBook(String bookId) throws IOException, InterruptedException {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be be null or empty!");
        }
        System.out.println(bookService.removeBook(this, bookId));
    }

    public void findBookById(String bookId) throws IOException, InterruptedException {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("BookId title cannot be be null or empty!");
        }
        System.out.println(bookService.findBookById(this, bookId));
    }

    public void findBookByName(String title) throws IOException, InterruptedException {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be be null or empty!");
        }
        System.out.println(bookService.findBookByName(this, title));
    }

    public void showAllBook() throws IOException, InterruptedException {
        System.out.println(bookService.showAllBook(this));
    }

    public void addUser(String stringId, String name, String password) throws IOException, InterruptedException {
        if (stringId == null || name == null || password == null) {
            throw new IllegalArgumentException("User details cannot be null");
        }
        Reader reader = new Reader(stringId, name, password);
        String response = userService.addReader(getCsrftoken(), getStringId(), getApi_KEY(), reader);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
    }

    public void updateBookName(String bookId, String title) throws IOException, InterruptedException {
        if (bookId == null || title == null) {
            throw new IllegalArgumentException("Book ID or title cannot be null");
        }
        System.out.println(bookService.updateBookName(this, bookId, title));
    }

    public void updateBookAuthor(String bookId, String author) throws IOException, InterruptedException {
        if (bookId == null || author == null) {
            throw new IllegalArgumentException("Book ID or author cannot be null");
        }
        System.out.println(bookService.updateAuthor(this, bookId, author));
    }

    public void addBookTag(String bookId, String bookTag) throws IOException, InterruptedException {
        if (bookId == null || bookTag == null) {
            throw new IllegalArgumentException("Book ID or tag cannot be null");
        }
        System.out.println(bookService.updateBookTag(this, bookId, bookTag));
    }

    public void removeBookTag(String bookId) throws IOException, InterruptedException {
        if (bookId == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        System.out.println(bookService.updateBookTag(this, bookId, null));
    }

    public void updateBookTag(String bookId, String newTag) throws IOException, InterruptedException {
        if (bookId == null || newTag == null) {
            throw new IllegalArgumentException("Book ID or new tag cannot be null");
        }
        System.out.println(bookService.updateBookTag(this, bookId, newTag));
    }

    public void findUser(String stringId) throws IOException, InterruptedException {
        if (stringId == null) {
            throw new IllegalArgumentException("stringId or new tag cannot be null");
        }
        Reader reader = new Reader(stringId);
        String response = userService.showReaderInfo(reader);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
    }
}
