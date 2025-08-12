package com.example.quanlithuvien;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UserService {
    /**
     * Thêm người đọc.
     *
     * @param csrfToken csrfToken
     * @param stringId  stringId
     * @param api_KEY   api_KEY
     * @param reader    Người dùng được thêm
     */
    public void addReader(String csrfToken, String stringId, String api_KEY, Reader reader) {
        //Dùng URLEncoder.encode để mã hóa các giá trị để tránh lỗi cú pháp URL
        //Ví dụ: Kí tự "=" có thể bị mã hóa thành "%3D"
        //StandardCharsets.UTF_8 là đối tượng Charset được định nghĩa sẵn, tương ứng với mã hóa UTF-8
        String url = "http://20.196.64.166:8080/librarian/addreader?" +
                "csrfToken=" + URLEncoder.encode(csrfToken, StandardCharsets.UTF_8) +
                "&stringId=" + URLEncoder.encode(stringId, StandardCharsets.UTF_8) +
                "&API_KEY=" + URLEncoder.encode(api_KEY, StandardCharsets.UTF_8);

        JsonSerializer<Reader> readerJsonSerializer = (reader1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("stringId", reader1.getStringId());
            jsonObject.addProperty("name", reader1.getName());
            jsonObject.addProperty("password", reader1.getPassword());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(reader);

        BookService bookService = new BookService();
        bookService.doPostRequest(url, json);
    }

    /**
     * Đăng nhập.
     *
     * @param reader Người dùng đăng nhập
     */
    public void login(Reader reader) {
        JsonSerializer<Reader> readerJsonSerializer = (reader1, type, jsonSerializationContext) -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("stringId", reader1.getStringId());
            jsonObject.addProperty("name", reader1.getName());
            jsonObject.addProperty("password", reader1.getPassword());
            return jsonObject;
        };

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Reader.class, readerJsonSerializer)
                .create();
        String json = gson.toJson(reader);

        BookService bookService = new BookService();
        bookService.doPostRequest("http://20.196.64.166:8080/login/authenticate", json);
    }
}
