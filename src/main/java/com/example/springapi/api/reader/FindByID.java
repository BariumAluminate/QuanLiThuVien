package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.CheckString;
import com.example.springapi.api.reader.basicclass.CheckUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/reader")
public class FindByID {
    @Autowired
    private CheckUser checkerUser;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/findid")
    public Reponsebook findBookById(@RequestBody BookReaderRequest request) {
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        if (checkerUser.check(reader)) {
            if (CheckString.isValid(book.getbookId())) {
                String sql = "SELECT * FROM BOOK WHERE STRING_ID_BOOK = ?";
                Bookclass foundBook = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{book.getbookId()},
                    new BeanPropertyRowMapper<>(Bookclass.class)
                );
                if (foundBook != null) {
                    String message = "Book found successfully";
                    foundBook.setbookId(book.getbookId());
                    return new Reponsebook(foundBook, message);
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid credentials or user not found");
        }
        return null;
    }
}
