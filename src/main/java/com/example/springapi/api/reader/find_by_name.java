package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.BookRowMapper;
import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.ListResponseBook;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.reader.basicclass.CheckString;

import java.util.List;

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

    @Autowired
    private CheckUser checkUser;

    @PostMapping("/find_by_name")
    public ListResponseBook findByName(@RequestBody BookReaderRequest bookReaderRequest) {
        String name = bookReaderRequest.getBook().getTitle();
        Reader reader = bookReaderRequest.getReader();

        if (!CheckString.isValid(name)) {
            return new ListResponseBook(null, "Invalid name format");
        }

        if (!checkUser.check(reader)) {
            return new ListResponseBook(null, "User not found");
        }

        String sql = "SELECT * FROM BOOK WHERE TITLE = ?";
        List<Bookclass> books = jdbcTemplate.query(
            sql,
            new Object[]{name},
            new BookRowMapper()
        );

        if (books == null || books.isEmpty()) {
            return new ListResponseBook(null, "No books found for this name");
        }

        return new ListResponseBook(books, "Books found");
    }
    
}
