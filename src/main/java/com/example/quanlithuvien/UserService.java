package com.example.quanlithuvien;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.example.quanlithuvien.Librarian.BASE_URL;

public class UserService {
    /**
     * Chuyển đổi đối tượng Reader thành đối tượng JSON với các cặp key-value cụ thể.
     * Hàm này tạo ra 1 JsonObject chứa các đối tượng stringId, csrftoken và api_KEY.
     */
    private static final JsonSerializer<Reader> readerJsonSerializer = (reader1, typeOfSrc, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stringId", reader1.getStringId());
        jsonObject.addProperty("name", reader1.getName());
        jsonObject.addProperty("password", reader1.getPassword());
        return jsonObject;
    };

    public static String makeJson(Reader reader) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        return gson.toJson(reader);
    }

    public boolean isLibrarian(Reader reader) throws IOException, InterruptedException {
        String response = showReaderInfo(reader);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        String role = jsonNode.get("librarian").asText();
        return role.equals("true");
    }

    /**
     * Thêm người đọc.
     *
     * @param csrfToken csrfToken
     * @param stringId  stringId
     * @param api_KEY   api_KEY
     * @param reader    Người dùng được thêm
     */
    public String addReader(String csrfToken, String stringId, String api_KEY, Reader reader)
            throws IOException, InterruptedException {
        //Dùng URLEncoder.encode để mã hóa các giá trị để tránh lỗi cú pháp URL
        //Ví dụ: Kí tự "=" có thể bị mã hóa thành "%3D"
        //StandardCharsets.UTF_8 là đối tượng Charset được định nghĩa sẵn, tương ứng với mã hóa UTF-8
        String url = "http://20.196.64.166:8080/librarian/addreader?" +
                "csrfToken=" + URLEncoder.encode(csrfToken, StandardCharsets.UTF_8) +
                "&stringId=" + URLEncoder.encode(stringId, StandardCharsets.UTF_8) +
                "&API_KEY=" + URLEncoder.encode(api_KEY, StandardCharsets.UTF_8);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(reader);

        BookService bookService = new BookService();
        HttpResponse<String> response = bookService.doPostRequest(url, json);
        return response.body();
    }

    /**
     * Đăng nhập.
     *
     * @param reader Người dùng đăng nhập
     */
    public static boolean login(Reader reader) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(reader);

        BookService bookService = new BookService();
        HttpResponse<String> response = bookService.doPostRequest(BASE_URL + "/login/authenticate", json);

        return response.statusCode() == 200;
    }

    /**
     * Ghi ra thông tin ngươi dùng.
     *
     * @param reader Người thực hiện yêu cầu
     */
    public String showReaderInfo(Reader reader) throws IOException, InterruptedException {
        JsonSerializer<Reader> readerJsonSerializer = (src, typeOfSrc, context) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("stringId", src.getStringId());
            jsonObject.addProperty("csrftoken", src.getCsrftoken());
            jsonObject.addProperty("api_KEY", src.getApi_KEY());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(reader);

        BookService bookService = new BookService();
        HttpResponse<String> response = bookService.doPostRequest(BASE_URL + "/reader/show", json);
        return response.body();
    }
}
