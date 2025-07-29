package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.book.Bookclass;
import com.example.springapi.api.book.BookReaderRequest;
import com.example.springapi.api.book.Reponsebook;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.checkstring;
import com.example.springapi.api.reader.basicclass.checkuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/reader")
public class find_book {

    @Autowired
    private checkstring checker;
    
    @Autowired
    private checkuser checkerUser;
    @PostMapping("/findid")
    public Reponsebook findBookById(@RequestBody BookReaderRequest request) {
        // Logic to find the book by ID
        // This is a placeholder implementation
        Bookclass book = request.getBook();
        Reader reader = request.getReader();
        if(checkerUser.check(reader)){
            if(checker.isValid(book.getbookId())){
                // Find the book by ID
                // This is a placeholder implementation
                // sql query to find the book by ID
                String message = "Book found successfully";
                return new Reponsebook(book, message);
            }
        } else{
            throw new IllegalArgumentException("Invalid credentials or user not found");
        }
    }
}
