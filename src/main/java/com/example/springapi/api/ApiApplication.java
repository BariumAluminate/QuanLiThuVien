package com.example.springapi.api;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController  // 👈 Thêm dòng này để đánh dấu class này là một REST Controller
public class ApiApplication {

	public static void main(String[] args) {	
		SpringApplication.run(ApiApplication.class, args);
		while (true) {
			connectSQL();
			
		}
	}

	// @GetMapping("/hello")
	// public String sayHello() {
	// 	return "Hello World";
	// }
	public static void connectSQL() {
		try {
			String sa = Files.readAllLines(Paths.get("usernames.txt")).get(0).trim();
			String pass = Files.readAllLines(Paths.get("password.txt")).get(0).trim();
			ProcessBuilder p = new ProcessBuilder("sqlcmd", "-S", "quanlithuvien.database.windows.net", "-U", sa, "-P", pass, "-d", "QuanLySach");
			Process proc=p.start();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	public static void query(String s){
		
	}
}
