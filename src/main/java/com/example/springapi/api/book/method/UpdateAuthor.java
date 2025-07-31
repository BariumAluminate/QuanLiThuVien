package com.example.springapi.api.book.method;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.reader.basicclass.CheckString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.springapi.api.reader.basicclass.CheckUser;
import com.example.springapi.api.reader.basicclass.Reader;

@RestController
@RequestMapping("/book")
public class UpdateAuthor {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/update-author")
    public Reponsebook updateAuthor(@RequestBody BookReaderRequest request) {
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        CheckUser checkUser = new CheckUser();
        if (!checkUser.check(reader)) {
            throw new IllegalArgumentException("Invalid user credentials or user not found");
        }
        if (!CheckString.isValid(book.getbookId())) {
            throw new IllegalArgumentException("Invalid book ID");
        }
        String sql = "UPDATE BOOK SET AUTHOR = ? WHERE STRING_ID_BOOK = ?";
        int rowsAffected = jdbcTemplate.update(sql, book.getAuthor(), book.getbookId());
        if (rowsAffected > 0) {
            return new Reponsebook(book, "Author updated successfully");
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }
}
