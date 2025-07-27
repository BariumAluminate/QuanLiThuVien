package com.example.springapi.api.reader.basicclass;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Reader {
    private String stringId;
    private String name;
    private String password;
    private String API_KEY;
    private String CSRFTOKEN;
    public Reader() {}

    public Reader(String stringId, String name, String password, String API_KEY, String CSRFTOKEN) {
        this.stringId = stringId;
        this.name = name;
        this.password = password;
        this.API_KEY = API_KEY;
        this.CSRFTOKEN = CSRFTOKEN;
    }

    // Getters and Setters
    public String getStringId() { return stringId; }
    public void setStringId(String stringId) { this.stringId = stringId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAPI_KEY() { return API_KEY; }
    public void setAPI_KEY(String API_KEY) { this.API_KEY = API_KEY; }

    public String getCSRFTOKEN() { return CSRFTOKEN; }
    public void setCSRFTOKEN(String CSRFTOKEN) { this.CSRFTOKEN = CSRFTOKEN; }

    //method
    public String createrandomkey() {
        // You can now use apiKey and csrfToken as needed
        java.util.UUID uuid = java.util.UUID.randomUUID();
        String ret = uuid.toString();   
        return ret;
    }
        public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // Chuyển sang dạng chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Hashing error", e);
        }
    }
}
