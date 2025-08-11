package com.example.quanlithuvien;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookService {
    private static final JsonSerializer<Reader> readerJsonSerializer = (src, typeOfSrc, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stringId", src.getStringId());
        jsonObject.addProperty("csrftoken", src.getCsrftoken());
        jsonObject.addProperty("api_KEY", src.getApi_KEY());
        return jsonObject;
    };

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

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://20.196.64.166:8080/librarian/addbook"))
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

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://20.196.64.166:8080/librarian/removebook"))
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

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://20.196.64.166:8080/reader/findid"))
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
     * Tìm sách theo tên sách.
     * @param reader Người tìm sách
     * @param title Tên sách cần tìm
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

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://20.196.64.166:8080/reader/find_by_name"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
