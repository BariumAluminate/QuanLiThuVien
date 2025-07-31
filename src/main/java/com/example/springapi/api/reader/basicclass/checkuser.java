package com.example.springapi.api.reader.basicclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component 
public class CheckUser {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CSRFTokenUtil csrftokenutil;
    public boolean check(Reader reader) { // param: ID, API_KEY, CSRF_TOKEN
        reader.setName("");
        reader.setPassword("");
        CheckString checker = new CheckString(reader.getStringId());
        if (!checker.isValid(reader.getStringId())) {
            throw new IllegalArgumentException("Invalid String ID: " + reader.getStringId());
        }
        checker.setNeedcheck(reader.getAPI_KEY());
        if (!checker.isValid(reader.getAPI_KEY())) {
            throw new IllegalArgumentException("Invalid API Key: " + reader.getAPI_KEY());
        }
        checker.setNeedcheck(reader.getCSRFTOKEN());
        if (!checker.isValid(reader.getCSRFTOKEN())) {
            throw new IllegalArgumentException("Invalid CSRF Token: " + reader.getCSRFTOKEN());
        }        
        try {
                reader.setCSRFTOKEN(csrftokenutil.encrypt(reader.getCSRFTOKEN()));
                //System.out.println(reader.getCSRFTOKEN());
        } catch (Exception e) {
            throw new RuntimeException("Không thể mã hóa CSRF token", e);
        }
        try{
            
            String sql = "SELECT COUNT(*) FROM READER WHERE STRING_ID = ? AND API_KEY = ? AND csrftoken = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class,
            reader.getStringId(),
            reader.getAPI_KEY(),
            reader.getCSRFTOKEN());
            if (count != null && count == 1) {
                String nameSql = "SELECT NAME FROM READER WHERE STRING_ID = ?";
                String name = jdbcTemplate.queryForObject(nameSql, String.class, reader.getStringId());
                reader.setName(name);
            }
            return count != null && count == 1;
        } catch (Exception e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public boolean checklibrarian(String csrftoken,String stringId, String API_KEY) {
        CheckString checker = new CheckString(stringId);
        if (!checker.isValid(stringId)) {
            throw new IllegalArgumentException("Invalid String ID: " + stringId);
        }
        checker.setNeedcheck(API_KEY);
        if (!checker.isValid(API_KEY)) {
            throw new IllegalArgumentException("Invalid API Key: " + API_KEY);
        }
        checker.setNeedcheck(csrftoken);
        if (!checker.isValid(csrftoken)) {
            throw new IllegalArgumentException("Invalid CSRF Token: " + csrftoken);
        }
        try {
            csrftoken = csrftokenutil.encrypt(csrftoken);
        } catch (Exception e) {
            throw new RuntimeException("Không thể mã hóa CSRF token", e);
        }
        
        String sql = "SELECT COUNT(*) FROM READER WHERE STRING_ID = ? AND API_KEY = ? AND csrftoken = ? AND LIBRARIAN = 1";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, stringId, API_KEY, csrftoken);
        return count != null && count == 1;
    }
}
