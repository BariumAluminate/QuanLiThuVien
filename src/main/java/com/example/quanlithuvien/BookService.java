package com.example.quanlithuvien;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookService {
    /**
     * Phương thức để thêm sách (Chỉ có tác dụng nếu là thủ thư).
     * @param reader Người thêm sách
     * @param book Sách được thêm
     * @return Nếu là thủ thư sách sẽ được thêm
     */
    public String addBook(Reader reader, Book book) {
        LibraryData libraryData = new LibraryData(reader, book);

        Gson gson = new Gson();
        String json = gson.toJson(libraryData);

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://20.196.64.166:8080/librarian/addbook"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return "Success: Book added - " + response.body();
            } else {
                return "Error: Failed to add book - " + response.body();
            }

        } catch (IOException | InterruptedException e) {
            return "Error: Failed to connect to API - " + e.getMessage();
        }
    }
}
