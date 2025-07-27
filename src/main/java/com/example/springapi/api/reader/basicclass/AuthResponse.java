package com.example.springapi.api.reader.basicclass;
import com.example.springapi.api.reader.basicclass.Reader;
/**
 * AuthResponse class represents the response structure for authentication operations.
 * It contains a Reader object and a message indicating the result of the authentication.
 */
public class AuthResponse {
    private Reader reader;
    private String message;

    public AuthResponse(Reader reader, String message) {
        this.reader = reader;
        this.message = message;
    }

    public Reader getReader() {
        return reader;
    }

    public String getMessage() {
        return message;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
