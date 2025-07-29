package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.checkstring;
import com.example.springapi.api.reader.basicclass.checkuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/reader")
public class find_book {
    private checkstring checkerSQL;
    
    @Autowired
    private checkuser checkerUser;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @PostMapping("/findid")
    public Reponsebook findBookById(@RequestBody BookReaderRequest request) {
        // Logic to find the book by ID
        // This is a placeholder implementation
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        checkerSQL = new checkstring(book.getbookId());
        if(checkerUser.check(reader)){
            if(checkerSQL.isValid(book.getbookId())){
                String sql = "SELECT * FROM BOOK WHERE STRING_ID_BOOK = ?";
                Bookclass foundBook = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{book.getbookId()},
                    new BeanPropertyRowMapper<>(Bookclass.class)
                );
                if(foundBook != null){
                    String message = "Book found successfully";
                    return new Reponsebook(foundBook, message);
                }
            }
        } else{
            throw new IllegalArgumentException("Invalid credentials or user not found");
        }
        return null;
    }
}
