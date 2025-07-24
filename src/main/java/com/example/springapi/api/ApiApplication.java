package com.example.springapi.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController  // 👈 Thêm dòng này để đánh dấu class này là một REST Controller
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World";
	}
    
}
