package com.example.springapi.api.reader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.ListResponseBook;
import com.example.springapi.api.reader.basicclass.CheckUser;
import com.example.springapi.api.reader.basicclass.Reader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("reader")
public class ShowAllBook {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CheckUser checkUser;
    @PostMapping("showAllBook")    
    public ListResponseBook showAllBook(@RequestBody Reader reader) {
        if(checkUser.check(reader) == false) {
            return new ListResponseBook(null, "Invalid user credentials");
        }
        String sql = "SELECT * FROM BOOK";
        List<Bookclass> books = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Bookclass book = new Bookclass();
            book.setbookId(rs.getString("STRING_ID_BOOK"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setBooktag(rs.getString("booktag"));
            book.setBorrowerId(rs.getString("BorrowerID"));
            return book;
        });

        ListResponseBook response = new ListResponseBook(books, "Books retrieved successfully");
        return response;
    }
}
