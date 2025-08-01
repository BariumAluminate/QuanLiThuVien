package com.example.springapi.api.reader.librarian;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.reader.basicclass.AuthResponse;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.CheckString;
import com.example.springapi.api.reader.basicclass.CheckUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/librarian")
public class FindUser {

    @Autowired
    private CheckUser checklibrarian;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/finduser")
    public AuthResponse findUser(@RequestBody Reader reader,@RequestParam String stringID) {
        if (checklibrarian.checklibrarian(reader.getCSRFTOKEN(), reader.getStringId(), reader.getAPI_KEY())) {
            // check sql injection for String ID
            if (!CheckString.isValid(stringID)) {
                throw new IllegalArgumentException("Invalid String ID: " + stringID);
            }
            String sql = "SELECT * FROM READER WHERE STRING_ID = ?";
            Reader foundReader = jdbcTemplate.queryForObject(sql, new Object[] { stringID }, (rs, rowNum) -> {
                Reader r = new Reader();
                r.setStringId(rs.getString("STRING_ID"));
                r.setName(rs.getString("NAME"));
                r.setPassword(rs.getString("PASSWORD"));
                r.setAPI_KEY(rs.getString("API_KEY"));
                r.setCSRFTOKEN(rs.getString("CSRFTOKEN"));
                return r;
            });
            if (foundReader != null) {
                return new AuthResponse(foundReader, "User found successfully");
            }
        } else {
            throw new IllegalArgumentException("Invalid librarian credentials or user not found or user not librarian");
        }
        return null;
    }
}
