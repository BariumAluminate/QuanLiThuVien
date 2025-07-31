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
public class find_by_booktag {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CheckUser checkUser;

    @PostMapping("/find_by_booktag")
    public ListResponseBook findByBookTag(@RequestBody BookReaderRequest bookReaderRequest) {
        String bookTag = bookReaderRequest.getBook().getBooktag();
        Reader reader = bookReaderRequest.getReader();
        if (!CheckString.isValid(bookTag)) {
            return new ListResponseBook(null,"Invalid book tag format");
        }

        if (!checkUser.check(reader)) {
            return new ListResponseBook(null,"User not found");
        }
        
        String sql = "SELECT * FROM BOOK WHERE BOOKTAG = ?";
        List<Bookclass> books = jdbcTemplate.query(
            sql,
            new Object[]{bookTag},
            new BookRowMapper()
        );

        if (books == null || books.isEmpty()) {
            return new ListResponseBook(null,"No books found for this tag");
        }

        return new ListResponseBook(books, "Books found");
    }
}
