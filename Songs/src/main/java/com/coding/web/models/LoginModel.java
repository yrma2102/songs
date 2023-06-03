package com.coding.web.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginModel {
	@Email(message = "email no válido")
	@NotBlank(message = "campo email no puede estar vacío")
	private String email;
	
	@NotBlank(message = "por favor ingresa una contraseña")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginModel() {
		
	}
	public LoginModel(String email, String password) {
		this.email = email;
		this.password = password;
	}

}
