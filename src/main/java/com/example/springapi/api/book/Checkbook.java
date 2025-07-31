package com.example.springapi.api.book;

import com.example.springapi.api.reader.basicclass.CheckString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CheckBook {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkbook(Bookclass book){
        String ID = book.getbookId();
        if (CheckString.isValid(ID)) {
            String sql = "SELECT COUNT(*) FROM BOOK WHERE STRING_ID_BOOK = ?";
            int count = jdbcTemplate.queryForObject(sql, new Object[]{ID}, Integer.class);
            return count > 0;
        } else {
            throw new IllegalArgumentException("Invalid book ID");
        }
    }
}
