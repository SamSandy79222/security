package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	
	@GetMapping("/user")
	public String getUser() {
		return "hello user";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "hello admin";
	}
}
