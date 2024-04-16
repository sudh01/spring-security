package com.example.loginservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Login {
	
	@Id
	private String email;
	private String password;
	private String role;
	private boolean isLogin;

}
