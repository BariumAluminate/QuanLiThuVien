package com.example.quanlithuvien;

import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

import static com.example.quanlithuvien.Librarian.BASE_URL;

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
     * @param url  Đường dẫn Url của endpoint API để gửi yêu cầu POST
     * @param json chuỗi JSON chứa dữ liệu yêu cầu
     */
    public static HttpResponse<String> doPostRequest(String url, String json)
            throws IOException, InterruptedException {
        // Kiểm tra đầu vào
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        if (json == null) {
            throw new IllegalArgumentException("JSON body cannot be null");
        }

        // Tạo HttpClient với timeout
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)) // 10 giây
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Request failed with status: " + response.statusCode()
                    + ", body: " + response.body());
        }
        return response;
    }

    /**
     * Phương thức để thêm sách (Chỉ có tác dụng nếu là thủ thư).
     *
     * @param reader Người thêm sách
     * @param book   Sách được thêm
     */
    public String addBook(Reader reader, Book book) throws IOException, InterruptedException {
        LibraryData libraryData = new LibraryData(reader, book);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        HttpResponse<String> response = doPostRequest(BASE_URL + "/librarian/addbook", json);
        return response.body();
    }

    /**
     * Phương thức để xóa sách (Chỉ có tác dụng nếu là thủ thư).
     *
     * @param reader Người xóa sách
     * @param bookId Id của sách bị xóa
     */
    public String removeBook(Reader reader, String bookId) throws IOException, InterruptedException {
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

        HttpResponse<String> response = doPostRequest(BASE_URL + "/librarian/removebook", json);
        return response.body();
    }

    /**
     * Tìm sách theo id sách.
     *
     * @param reader người tìm sách
     * @param bookId id của sách cần tìm
     */
    public Book findBookById(Reader reader, String bookId) throws IOException, InterruptedException {
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

        HttpResponse<String> response = doPostRequest(BASE_URL + "/reader/findid", json);

        try {
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (!jsonResponse.has("message") ||
                    !jsonResponse.get("message").getAsString().equals("Book found successfully")) {
                throw new IOException("Can't find book with ID");
            }

            Book bookFounded = gson.fromJson(jsonResponse.get("book"), Book.class);
            return bookFounded;
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Tìm sách theo tên sách.
     *
     * @param reader Người tìm sách
     * @param title  Tên sách cần tìm
     */
    public ArrayList<Book> findBookByName(Reader reader, String title) throws IOException, InterruptedException {
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

        HttpResponse<String> response = doPostRequest(BASE_URL + "/reader/find_by_name", json);

        try {
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (!jsonResponse.has("message") ||
                    !jsonResponse.get("message").getAsString().equals("Books found")) {
                throw new IOException("Failed to find book");
            }

            JsonArray booksArray = jsonResponse.getAsJsonArray("books");
            ArrayList<Book> bookArrayList = new ArrayList<>();

            for (JsonElement element : booksArray) {
                Book book1 = gson.fromJson(element, Book.class);
                bookArrayList.add(book1);
            }

            return bookArrayList;
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Ghi ra thông tin sách trong thư viện.
     *
     * @param reader Người gửi yêu cầu
     */
    public static ArrayList<Book> showAllBook(Reader reader) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(reader);

        HttpResponse<String> response = doPostRequest(BASE_URL + "/reader/showAllBook", json);

        try {
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            if (!jsonResponse.has("message") ||
                    !jsonResponse.get("message").getAsString().equals("Books retrieved successfully")) {
                throw new IOException("Failed to retrieve book!");
            }

            JsonArray booksArray = jsonResponse.getAsJsonArray("books");
            ArrayList<Book> bookArrayList = new ArrayList<>();

            for (JsonElement element : booksArray) {
                Book book = gson.fromJson(element, Book.class);
                bookArrayList.add(book);
            }

            return bookArrayList;
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Mượn sách thông qua id sách.
     *
     * @param reader người mượn
     * @param bookId Id của sách cần mượn
     */
    public String borrowBook(Reader reader, String bookId) throws IOException, InterruptedException {
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

        HttpResponse<String> response = doPostRequest(BASE_URL + "/reader/borrow", json);
        return response.body();
    }

    /**
     * Cập nhật tên sách có id là bookId thành title.
     *
     * @param reader Người cập nhật
     * @param bookId Id của sách cần cập nhật
     * @param title  Tên sách sau cập nhật
     */
    public String updateBookName(Reader reader, String bookId, String title) throws IOException, InterruptedException {
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

        HttpResponse<String> response = doPostRequest(BASE_URL + "/book/updatename", json);
        return response.body();
    }

    /**
     * Tìm thông tin các quyển sách có tag là bookTag.
     *
     * @param reader  người tìm
     * @param bookTag bookTag cần tìm
     */
    public ArrayList<Book> findBookByBookTag(Reader reader, String bookTag) throws IOException, InterruptedException {
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

        HttpResponse<String> response = doPostRequest(BASE_URL + "/reader/find_by_tag", json);

        try {
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            if (!jsonResponse.has("message") ||
                    jsonResponse.get("message").getAsString().equals("Books found")) {
                throw new IOException("Cant find book");
            }

            JsonArray bookArray = jsonResponse.getAsJsonArray("books");
            ArrayList<Book> bookArrayList = new ArrayList<>();

            for (JsonElement element : bookArray) {
                Book book1 = gson.fromJson(element, Book.class);
                bookArrayList.add(book1);
            }
            return bookArrayList;
        } catch (JsonParseException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Cập nhật bookTag vào bookId.
     *
     * @param reader  Người cập nhật
     * @param bookId  Id của sách cần cập nhật
     * @param bookTag Tag dùng để cập nhật
     */
    public String updateBookTag(Reader reader, String bookId, String bookTag) throws IOException, InterruptedException {
        Book book = new Book();
        book.setBookId(bookId);
        book.setBookTag(bookTag);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId", book1.getBookId());
            jsonObject.addProperty("booktag", book1.getBookTag());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        HttpResponse<String> response = doPostRequest(BASE_URL + "/book/update-book-tag", json);
        return response.body();
    }

    /**
     * Cập nhật tác giả cuốn sách.
     *
     * @param reader Nguười cập nhật
     * @param bookId Id của sách cần cập nhật
     * @param author Bút danh tác giả dùng để cập nhật
     */
    public String updateAuthor(Reader reader, String bookId, String author) throws IOException, InterruptedException {
        Book book = new Book();
        book.setBookId(bookId);
        book.setAuthor(author);

        LibraryData libraryData = new LibraryData(reader, book);

        JsonSerializer<Book> bookJsonSerializer = (book1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("bookId", book1.getBookId());
            jsonObject.addProperty("author", book1.getAuthor());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .registerTypeAdapter(Book.class, bookJsonSerializer)
                .create();
        String json = gson.toJson(libraryData);

        HttpResponse<String> response = doPostRequest(BASE_URL + "/book/update-author", json);
        return response.body();
    }
}
