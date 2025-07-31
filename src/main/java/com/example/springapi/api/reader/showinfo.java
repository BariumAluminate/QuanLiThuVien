package com.example.springapi.api.reader;

import org.springframework.web.bind.annotation.RestController;

import com.example.springapi.api.reader.basicclass.AuthResponse;
import com.example.springapi.api.reader.basicclass.CSRFTokenUtil;
import com.example.springapi.api.reader.basicclass.Reader;
import com.example.springapi.api.reader.basicclass.CheckUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/reader")
@Component 
public class showinfo {
    @Autowired
    private CheckUser checker;

    @Autowired
    private CSRFTokenUtil csrfTokenUtil;

    @PostMapping("/show")
    public AuthResponse show(@RequestBody Reader reader) {  // param: ID, API_KEY, CSRF_TOKEN
        AuthResponse response = new AuthResponse(null, null);
        if(checker.check(reader)==true) {
            try {
                reader.setCSRFTOKEN(csrfTokenUtil.decrypt(reader.getCSRFTOKEN()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(checker.checklibrarian(reader.getCSRFTOKEN(), reader.getStringId(), reader.getAPI_KEY())) {
                reader.setisLibrarian(true);
            } else {
                reader.setisLibrarian(false);
            }
            response.setReader(reader);
            response.setMessage("User information retrieved successfully.");
        } else {
            throw new IllegalArgumentException("Invalid credentials or user not found");
        }
        return response;
    }
}
