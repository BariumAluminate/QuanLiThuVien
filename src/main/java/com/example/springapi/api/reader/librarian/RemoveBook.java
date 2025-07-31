package com.example.springapi.api.reader.librarian;


import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.CheckString;
import com.example.springapi.api.reader.basicclass.CheckUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/librarian")
public class RemoveBook {
    
    private CheckString checkerSQL;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CheckUser checkuser;

    @PostMapping("/removebook")
    public String Removebook(@RequestBody BookReaderRequest request) {
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        if(checkerSQL.isValid(book.getbookId()) && checkuser.check(reader)) {
            String sql = "DELETE FROM BOOK WHERE STRING_ID_BOOK = ?";
            jdbcTemplate.update(sql, book.getbookId());
            return "Book " + book.getbookId() + " removed successfully";
        } else {
            throw new IllegalArgumentException("Invalid credentials or user not found");
        }
    }
}
