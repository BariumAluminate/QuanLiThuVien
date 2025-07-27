package com.example.springapi.api.reader.basicclass;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecretKeyProvider {

    @Value("${app.secret-key}")
    private String secretKey ;
    
    public String getSecretKey() {
        return secretKey;
    }
} 
    
