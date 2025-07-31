package com.example.springapi.api.book.method;

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
@RequestMapping("/book")
public class UpdateName {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CheckUser checkuser;

    
    @PostMapping("/updatename")
    public String Update (@RequestBody BookReaderRequest request) {  // reader: name,csrftoken,API_KEY   book: bookID , title (new)
        Bookclass book = request.getBook();
        Reader reader = request.getReader();

        if (checkuser.check(reader) && CheckString.isValid(book.getTitle()) && CheckString.isValid(book.getbookId())) {
            String sql = "UPDATE BOOK SET title = ? WHERE STRING_ID_BOOK = ?";
            jdbcTemplate.update(sql, book.getTitle(), book.getbookId());
            return "Book name updated successfully for " + book.getbookId();
        } else {
            throw new IllegalArgumentException("Invalid user credentials or user not found");
        }
    }
}
