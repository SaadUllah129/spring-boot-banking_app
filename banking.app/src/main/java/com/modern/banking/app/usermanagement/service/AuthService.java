package com.modern.banking.app.usermanagement.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modern.banking.app.usermanagement.dto.LoginDto;
import com.modern.banking.app.usermanagement.model.User;
import com.modern.banking.app.usermanagement.repository.UserRepository;
import com.modern.banking.app.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	public String authenticateUser(LoginDto loginDTO) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		User user = userRepository.findByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return jwtUtil.generateToken(user.getEmail());
	}

}
