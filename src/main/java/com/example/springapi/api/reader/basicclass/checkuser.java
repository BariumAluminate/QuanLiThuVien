package com.example.springapi.api.reader.basicclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component 
public class checkuser {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CSRFTokenUtil csrftokenutil;
    public boolean check(Reader reader) { // param: ID, API_KEY, CSRF_TOKEN
        reader.setName("");
        reader.setPassword("");
        checkstring checker = new checkstring(reader.getStringId());
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
}
