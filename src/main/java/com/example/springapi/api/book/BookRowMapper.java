package com.example.springapi.api.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.springapi.api.book.Bookclass;

public class BookRowMapper implements RowMapper<Bookclass> {
    @Override
    public Bookclass mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bookclass book = new Bookclass();
        book.setbookId(rs.getString("STRING_ID_BOOK"));
        book.setTitle(rs.getString("TITLE"));
        book.setAuthor(rs.getString("AUTHOR"));
        book.setBooktag(rs.getString("BOOKTAG"));
        book.setBorrowerId(rs.getString("BORROWERID")); // nếu bạn có trường này
        return book;
    }
}