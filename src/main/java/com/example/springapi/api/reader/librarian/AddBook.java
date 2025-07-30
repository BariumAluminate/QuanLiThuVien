package com.example.springapi.api.reader.librarian;


import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.checkuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/librarian")
public class AddBook {
 
    private checkuser checklibrarian;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/addbook")
    
    public Reponsebook Addbook (@RequestBody BookReaderRequest request) {
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        if(checklibrarian.checklibrarian(reader.getCSRFTOKEN(), reader.getStringId(), reader.getAPI_KEY())) {
            String sql = "INSERT INTO BOOK (title,author,booktag,BorrowerID,STRING_ID_BOOK) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getBooktag(), book.getBorrowerId(), book.getbookId());
            String message = "Book added successfully";
            Reponsebook response = new Reponsebook(book, message);  
            return response;
        } else {
            throw new IllegalArgumentException("Invalid librarian credentials or user not found");
        }
    }
}
