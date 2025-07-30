package com.example.springapi.api.reader.librarian;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.reader.basicclass.CSRFTokenUtil;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.checkstring;
import com.example.springapi.api.reader.basicclass.checkuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.jdbc.core.JdbcTemplate;
@RestController
@RequestMapping("/librarian")
public class AddReader {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CSRFTokenUtil csrftokenutil;

    private checkuser checker;

    public void runcommandaddreader(Reader reader) {
        checkstring checker = new checkstring(reader.getStringId());
        if (!checker.isValid(reader.getStringId())) {
            throw new IllegalArgumentException("Invalid String ID: " + reader.getStringId());
        }
        // check if stringId exist already in the database
        String checkSql = "SELECT COUNT(*) FROM READER WHERE STRING_ID = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{reader.getStringId()}, Integer.class);
        if (count != null && count > 0) {
            throw new IllegalArgumentException("String ID already exists: " + reader.getStringId());
        }
        
        checker.setNeedcheck(reader.getName());
        if (!checker.isValid(reader.getName())) {
            throw new IllegalArgumentException("Invalid Name: " + reader.getName());
        }
        checker.setNeedcheck(reader.getPassword());
        if (!checker.isValid(reader.getPassword())) {
            throw new IllegalArgumentException("Invalid Password: " + reader.getPassword());
        }
        reader.setAPI_KEY(reader.createrandomkey());
        reader.setPassword(Reader.hashPassword(reader.getPassword()));
        System.out.println("Bắt đầu mã hóa CSRF token...");
        try {
            reader.setCSRFTOKEN(csrftokenutil.encrypt(reader.createrandomkey()));
        } catch (Exception e) {
            throw new RuntimeException("Không thể mã hóa CSRF token", e);
        }
        System.out.println("Bắt đầu ghi dữ liệu vào DB...");
        String sql = "INSERT INTO READER (STRING_ID, NAME, PASSWORD, API_KEY, csrftoken) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, reader.getStringId(), reader.getName(), reader.getPassword(), reader.getAPI_KEY(), reader.getCSRFTOKEN());
        System.out.println("Ghi xong!");
    }
    @PostMapping("/addreader")
    public String addReader(@RequestBody Reader reader, @RequestParam String csrfToken, @RequestParam String stringId, @RequestParam String API_KEY) {
    if(!checker.checklibrarian(csrfToken, stringId, API_KEY)) {
        throw new IllegalArgumentException("You are not a librarian or CSRF token is invalid");
    }
    reader.setAPI_KEY(reader.createrandomkey());
    runcommandaddreader(reader);
    return "Reader added successfully with ID: " + reader.getStringId();
    }
}
