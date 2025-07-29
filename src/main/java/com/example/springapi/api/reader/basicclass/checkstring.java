package com.example.springapi.api.reader.basicclass;

import java.util.regex.Pattern;

public class checkstring {
    private String needcheck;

    public checkstring(String needcheck) {
        this.needcheck = needcheck;
    }
    public String getNeedcheck() {
        return needcheck;
    }
    public void setNeedcheck(String needcheck) {
        this.needcheck = needcheck;
    }
    public boolean isValid(String needcheck) {
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
