package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
private AuthenticationManager authenticationManager;
@Autowired
private PasswordEncoder passwordEncoder;
	
@Autowired
private UserRepository userRepository;
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginDto loginDto) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameoremail(),loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "succesfully login";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> userSignUp(@RequestBody SignUpDto signUpDto) {
		
		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("username already exist",HttpStatus.BAD_GATEWAY);
		}
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("email already exist",HttpStatus.BAD_GATEWAY);
		}
		
		User user=new User();
		user.setEmail(signUpDto.getEmail());
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		user.setRoles(signUpDto.getRoles());
		
		userRepository.save(user);
		
		return new ResponseEntity<>("user regiter succesfully",HttpStatus.CREATED);
		
	}
}
