package com.modern.banking.app.usermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modern.banking.app.usermanagement.dto.AuthResponse;
import com.modern.banking.app.usermanagement.dto.LoginDto;
import com.modern.banking.app.usermanagement.dto.CreateUserRequest;
import com.modern.banking.app.usermanagement.dto.UserResponse;
import com.modern.banking.app.usermanagement.service.AuthService;
import com.modern.banking.app.usermanagement.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody CreateUserRequest userDTO) {
		return ResponseEntity.ok(userService.registerUser(userDTO));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDTO) {
		AuthResponse authResponse = authService.authenticateUser(loginDTO);
		return ResponseEntity.ok(authResponse);
	}
	
	@PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestParam String refreshToken) {
        AuthResponse authResponse = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(authResponse);
    }
}
