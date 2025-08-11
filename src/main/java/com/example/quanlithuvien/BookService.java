package com.example.quanlithuvien;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookService {
    /**
     * Chuyển đổi đối tượng Reader thành đối tượng JSON với các cặp key-value cụ thể.
     * Hàm này tạo ra 1 JsonObject chứa các đối tượng stringId, csrftoken và api_KEY.
     */
    private static final JsonSerializer<Reader> readerJsonSerializer = (src, typeOfSrc, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stringId", src.getStringId());
        jsonObject.addProperty("csrftoken", src.getCsrftoken());
        jsonObject.addProperty("api_KEY", src.getApi_KEY());
        return jsonObject;
    };

    /**
     * Gửi 1 yêu cầu POST đến endpoint API được chỉ định với dữ liệu JSON.
     * Phản hồi từ server sẽ được in ra phần console, nếu xảy ra lỗi sẽ ghi ra thông báo lỗi.
     *
     * @param Url  Đường dẫn Url của endpoint API để gửi yêu cầu POST
     * @param json chuỗi JSON chứa dữ liệu yêu cầu
     */
    private static void doPostRequest(String Url, String json) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(Url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Phương thức để thêm sách (Chỉ có tác dụng nếu là thủ thư).
     *
     * @param reader Người thêm sách
     * @param book   Sách được thêm
     */
    public void addBook(Reader reader, Book book) {
        LibraryData libraryData = new LibraryData(reader, book);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/librarian/addbook", json);
    }

    /**
     * Phương thức để xóa sách (Chỉ có tác dụng nếu là thủ thư).
     *
     * @param reader Người xóa sách
     * @param bookId Id của sách bị xóa
     */
    public void removeBook(Reader reader, String bookId) {
        Book book = new Book();
        book.setBookId(bookId);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId", book1.getBookId());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/librarian/removebook", json);
    }

    /**
     * Tìm sách theo id sách.
     *
     * @param reader người tìm sách
     * @param bookId id của sách cần tìm
     */
    public void findBookById(Reader reader, String bookId) {
        Book book = new Book();
        book.setBookId(bookId);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId", book1.getBookId());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/reader/findid", json);
    }

    /**
     * Tìm sách theo tên sách.
     *
     * @param reader Người tìm sách
     * @param title  Tên sách cần tìm
     */
    public void findBookByName(Reader reader, String title) {
        Book book = new Book();
        book.setTitle(title);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("title", book1.getTitle());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/reader/find_by_name", json);
    }

    /**
     * Ghi ra thông tin sách trong thư viện.
     *
     * @param reader Người gửi yêu cầu
     */
    public void showAllBook(Reader reader) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(reader);

        doPostRequest("http://20.196.64.166:8080/reader/showAllBook", json);
    }

    /**
     * Mượn sách thông qua id sách.
     *
     * @param reader người mượn
     * @param bookId Id của sách cần mượn
     */
    public void borrowBook(Reader reader, String bookId) {
        Book book = new Book();
        book.setBookId(bookId);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId", book1.getBookId());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/reader/borrow", json);
    }

    /**
     * Cập nhật tên sách có id là bookId thành title.
     *
     * @param reader Người cập nhật
     * @param bookId Id của sách cần cập nhật
     * @param title  Tên sách sau cập nhật
     */
    public void updateBookName(Reader reader, String bookId, String title) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setTitle(title);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId", book1.getBookId());
            jsonObject.addProperty("title", book1.getTitle());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/book/updatename", json);
    }

    /**
     * Tìm thông tin các quyển sách có tag là bookTag.
     *
     * @param reader  người tìm
     * @param bookTag bookTag cần tìm
     */
    public void findBookByBookTag(Reader reader, String bookTag) {
        Book book = new Book();
        book.setBookTag(bookTag);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("booktag", book1.getBookTag());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/reader/find_by_booktag", json);
    }

    /**
     * Cập nhật bookTag vào bookId.
     *
     * @param reader Người cập nhật
     * @param bookId Id của sách cần cập nhật
     * @param bookTag Tag dùng để cập nhật
     */
    public void updateBookTag(Reader reader, String bookId, String bookTag) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setBookTag(bookTag);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId",book1.getBookId());
            jsonObject.addProperty("bookTag",book1.getBookTag());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class,readerJsonSerializer)
                .registerTypeAdapter(Book.class,bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/book/update-book-tag",json);
    }

    /**
     * Cập nhật tác giả cuốn sách.
     *
     * @param reader Nguười cập nhật
     * @param bookId Id của sách cần cập nhật
     * @param author Bút danh tác giả dùng để cập nhật
     */
    public void updateAuthor(Reader reader, String bookId, String author) {
        Book book = new Book();
        book.setBookId(bookId);
        book.setAuthor(author);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId",book1.getBookId());
            jsonObject.addProperty("author",book1.getAuthor());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class,readerJsonSerializer)
                .registerTypeAdapter(Book.class,bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        doPostRequest("http://20.196.64.166:8080/book/update-author",json);
    }
}
