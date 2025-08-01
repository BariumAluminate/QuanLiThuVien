package com.example.springapi.api.reader.basicclass;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class CheckString {
    private String needcheck;

    public CheckString() {
        this.needcheck = "";
    }
    public CheckString(String needcheck) {
        this.needcheck = needcheck;
    }
    public String getNeedcheck() {
        return needcheck;
    }
    public void setNeedcheck(String needcheck) {
        this.needcheck = needcheck;
    }
    public static boolean isValid(String needcheck) {
        if (needcheck == null || needcheck.trim().isEmpty()) {
            return false;
        }

        String lower = needcheck.toLowerCase();

        // Dùng regex để match từ khóa độc lập
        String[] sqlKeywords = {
            "select", "insert", "update", "delete", "drop", "alter", "truncate", 
            "exec", "--", ";", "/\\*", "\\*/", "xp_", "union", "\\bor\\b", "\\band\\b"
        };

        for (String keyword : sqlKeywords) {
            if (Pattern.compile(keyword).matcher(lower).find()) {
                return false;
            }
        }

        return true;
    }
}
