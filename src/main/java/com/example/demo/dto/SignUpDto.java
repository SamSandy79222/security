package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
public class SignUpDto {

	
	private String name;
	private String username;
	private String email;
	private String password;

	private List<Role> roles;
	
	
}
