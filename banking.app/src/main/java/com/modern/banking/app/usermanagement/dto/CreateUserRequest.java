package com.modern.banking.app.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
	@NotBlank(message = "First Name cannot be blank")
	private String firstName;
	
	@NotBlank(message = "Last Name cannot be blank")
	private String lastName;
	
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@Size(min = 8, message = "Passowrd must be atleast 8 characters")
	private String password;
	
	@NotBlank(message = "Phone number is required")
	private String phone;
}
