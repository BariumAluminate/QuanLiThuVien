package com.example.springapi.api.book.method;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.checkstring;
import com.example.springapi.api.reader.basicclass.checkuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/book")
public class UpdateName {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private checkuser checkuser;
    
    private checkstring checkSQL;
    @PostMapping("/updatename")
    public String Updatename (@RequestBody BookReaderRequest request,@RequestParam String bookID) {
        Bookclass book = request.getBook();
        Reader reader = request.getReader();

        if (checkuser.check(reader) && checkSQL.isValid(book.getTitle()) && checkSQL.isValid(bookID)) {
            String sql = "UPDATE BOOK SET title = ? WHERE STRING_ID_BOOK = ?";
            jdbcTemplate.update(sql, book.getTitle(), bookID);
            return "Book name updated successfully for " + bookID;
        } else {
            throw new IllegalArgumentException("Invalid user credentials or user not found");
        }
    }
}
