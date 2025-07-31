package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.CheckBook;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.CheckUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/reader")
public class Borrow {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CheckBook checkerbook;

    private CheckUser checkuser;

    @PostMapping("/borrow")
    public Reponsebook borrowBook(@RequestBody BookReaderRequest bookReaderRequest) {
        String bookId = bookReaderRequest.getBook().getbookId();
        Reader reader = bookReaderRequest.getReader();
        if (checkerbook.checkbook(bookReaderRequest.getBook()) && checkuser.check(reader)) {
            String sql = "UPDATE BOOK SET STRING_ID_BORROWER = ? WHERE STRING_ID_BOOK = ?";
            jdbcTemplate.update(sql, reader.getStringId(), bookId);
            return new Reponsebook(bookReaderRequest.getBook(),"Borrowed successfully" + bookReaderRequest.getBook().getbookId() + " for " + reader.getStringId());
        } else {
            throw new IllegalArgumentException("Invalid book ID or user not found");
        }

    }
}
