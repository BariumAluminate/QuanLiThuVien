package com.example.springapi.api.reader;

import com.example.springapi.api.reader.basicclass.AuthResponse;
import com.example.springapi.api.reader.basicclass.CSRFTokenUtil;
import com.example.springapi.api.reader.basicclass.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/login")
public class login {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CSRFTokenUtil csrftokenutil;
    public boolean checkaccount(Reader reader) {
        checkstring checker = new checkstring(reader.getStringId());
        if (!checker.isValid(reader.getStringId())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        checker.setNeedcheck(reader.getName());
        if (!checker.isValid(reader.getName())) {
            throw new IllegalArgumentException("Invalid credentials ");
        }
        checker.setNeedcheck(reader.getPassword());
        if (!checker.isValid(reader.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials ");
        }
        String sql = "SELECT COUNT(*) FROM READER WHERE STRING_ID = ? AND NAME = ? AND PASSWORD = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class,
        reader.getStringId(),
        reader.getName(),
        Reader.hashPassword(reader.getPassword()));
        return count != null && count > 0;
    }

    public String takeAPIKEY(Reader reader) {
        String sql = "SELECT API_KEY FROM READER WHERE STRING_ID = ?";

        List<String> results = jdbcTemplate.query(sql, new Object[]{reader.getStringId()}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("API_KEY");
            }
        });

        return results.isEmpty() ? null : results.get(0);
    }
    public String takecsrftoken(Reader reader) {
        String sql = "SELECT csrftoken FROM READER WHERE STRING_ID = ?";

        List<String> results = jdbcTemplate.query(sql, new Object[]{reader.getStringId()}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("csrftoken");
            }
        });

        return results.isEmpty() ? null : results.get(0);
    }
    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody Reader loginRequest) {
        Reader reader = new Reader();
        reader.setName(loginRequest.getName());
        reader.setPassword(loginRequest.getPassword());
        reader.setStringId(loginRequest.getStringId());
        reader.setAPI_KEY("");
        reader.setCSRFTOKEN("");

        if (checkaccount(reader)) {
            reader.setAPI_KEY(takeAPIKEY(reader));
            try {
                reader.setCSRFTOKEN(csrftokenutil.decrypt(reader.getCSRFTOKEN()));
            } catch (Exception e) {
                throw new RuntimeException("Không thể giải mã CSRF token", e);
            }

            String message = "Login successful for user: " + reader.getStringId() + " and " + reader.getName();
            return new AuthResponse(reader, message);
        } else {
            throw new IllegalArgumentException("Invalid credentials for user: " + reader.getStringId() + " and " + reader.getName());
        }
    }
}
