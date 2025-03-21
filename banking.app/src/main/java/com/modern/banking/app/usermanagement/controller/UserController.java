package com.modern.banking.app.usermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.banking.app.usermanagement.dto.UserDto;
import com.modern.banking.app.usermanagement.dto.UserResponseDto;
import com.modern.banking.app.usermanagement.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
	 private final UserService userService;

	    @PostMapping("/register")
	    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserDto userDTO) {
	        return ResponseEntity.ok(userService.registerUser(userDTO));
	    }
}
