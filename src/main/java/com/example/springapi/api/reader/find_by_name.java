package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.reader.basicclass.CheckString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.springapi.api.reader.basicclass.CheckUser;
import com.example.springapi.api.reader.basicclass.Reader;

@RestController
@RequestMapping("/reader")
public class find_by_name {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/find_by_name")
    public Reponsebook findByName(@RequestBody BookReaderRequest bookReaderRequest) {
        String name = bookReaderRequest.getReader().getName();
        Reader reader = bookReaderRequest.getReader();
        if (!CheckString.isValid(name)) {
            return new Reponsebook(null,"Invalid name format");
        }

        CheckUser checkUser = new CheckUser();
        if (!checkUser.check(reader)) {
            return new Reponsebook(null,"User not found");
        }
        String sql = "SELECT * FROM BOOK WHERE NAME = ?";
        Bookclass book = jdbcTemplate.queryForObject(
            sql,
            new Object[]{name},
            new BeanPropertyRowMapper<>(Bookclass.class)
        );
        if (book == null) {
            return new Reponsebook(null,"No books found for this name");
        }
        return new Reponsebook(book, "Books found");
    }
    
}
