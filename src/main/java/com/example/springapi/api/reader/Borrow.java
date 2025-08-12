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

    @Autowired
    private CheckBook checkerbook;

    @Autowired
    private CheckUser checkuser;

    @PostMapping("/borrow")
    public Reponsebook borrowBook(@RequestBody BookReaderRequest bookReaderRequest) {
        String bookId = bookReaderRequest.getBook().getbookId();
        Reader reader = bookReaderRequest.getReader();
        if (checkerbook.checkbook(bookReaderRequest.getBook()) && checkuser.check(reader)) {
            String sql = "UPDATE BOOK SET BORROWERID = ? WHERE STRING_ID_BOOK = ? AND BORROWERID IS NULL";
            jdbcTemplate.update(sql, reader.getStringId(), bookId);
            if(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK WHERE STRING_ID_BOOK = ? AND BORROWERID = ?", 
                new Object[]{bookId, reader.getStringId()}, Integer.class) == 0) {
                return new Reponsebook(null, "Book is already borrowed or does not exist");
            }
            bookReaderRequest.getBook().setBorrowerId(reader.getStringId());
            return new Reponsebook(bookReaderRequest.getBook(),"Borrowed successfully " + bookReaderRequest.getBook().getbookId() + " for " + reader.getStringId());
        } else {
            throw new IllegalArgumentException("Invalid book ID or user not found");
        }

    }
}
