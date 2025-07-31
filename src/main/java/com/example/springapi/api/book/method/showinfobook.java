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
public class showinfobook {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/show-info")
    public Reponsebook showInfo(@RequestBody BookReaderRequest request) {   // parameters: reader: name, csrftoken, API_KEY; book: bookID
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        CheckUser checkUser = new CheckUser();
        if (!checkUser.check(reader)) {
            throw new IllegalArgumentException("Invalid user credentials or user not found");
        }
        if (!CheckString.isValid(book.getbookId())) {
            throw new IllegalArgumentException("Invalid book ID");
        }
        String sql = "SELECT * FROM BOOK WHERE STRING_ID_BOOK = ?";
        Bookclass bookInfo = jdbcTemplate.queryForObject(sql, new Object[]{book.getbookId()}, (rs, rowNum) -> {
            Bookclass b = new Bookclass();
            b.setbookId(rs.getString("STRING_ID_BOOK"));
            b.setTitle(rs.getString("TITLE"));
            b.setAuthor(rs.getString("AUTHOR"));
            b.setBooktag(rs.getString("booktag"));
            return b;
        });
        return new Reponsebook(bookInfo, "Book information retrieved successfully");
    }
}
