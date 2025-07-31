package com.example.springapi.api.reader.librarian;


import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.CheckBook;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.Reponsebook;
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
public class AddBook {
 
    @Autowired
    private CheckUser checklibrarian;
    @Autowired
    private JdbcTemplate jdbctemplate;
    @Autowired
    private CheckBook checkBook;
    @Autowired
    private CheckString checkerSQL;
    
    

    @PostMapping("/addbook")
    public Reponsebook Addbook (@RequestBody BookReaderRequest request) {
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        System.out.println("✅ jdbcTemplate: " + jdbctemplate);  // <--- Dòng debug
        if(checkBook.checkbook(book)) {
            throw new IllegalArgumentException("Book already exists with ID: " + book.getbookId());
        }
        if(checklibrarian.checklibrarian(reader.getCSRFTOKEN(), reader.getStringId(), reader.getAPI_KEY())) {
            String sql = "INSERT INTO BOOK (title,author,booktag,BorrowerID,STRING_ID_BOOK) VALUES (?,?,?,?,?)";
            jdbctemplate.update(sql, book.getTitle(), book.getAuthor(), book.getBooktag(), book.getBorrowerId(), book.getbookId());
            String message = "Book added successfully";
            Reponsebook response = new Reponsebook(book, message);  
            return response;
        } else {
            throw new IllegalArgumentException("Invalid librarian credentials or user not found");
        }
    }
}
