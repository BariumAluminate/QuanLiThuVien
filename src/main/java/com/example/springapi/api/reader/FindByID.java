package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.CheckString;
import com.example.springapi.api.reader.basicclass.CheckUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
                try {
                    String sql = "SELECT * FROM BOOK WHERE STRING_ID_BOOK = ?";
                    Bookclass foundBook = jdbcTemplate.queryForObject(
                        sql,
                        new Object[]{book.getbookId()},
                        new BeanPropertyRowMapper<>(Bookclass.class)
                    );

                    String message = "Book found successfully";
                    foundBook.setbookId(book.getbookId());
                    return new Reponsebook(foundBook, message);

                } catch (EmptyResultDataAccessException e) {
                    // Không tìm thấy bản ghi
                    return new Reponsebook(null, "Book not found with ID: " + book.getbookId());
                } catch (Exception e) {
                    // Các lỗi khác
                    return new Reponsebook(null, "Error occurred: " + e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid credentials or user not found");
        }
        return null;
    }
}
