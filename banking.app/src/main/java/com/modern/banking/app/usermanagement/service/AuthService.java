package com.modern.banking.app.usermanagement.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modern.banking.app.usermanagement.dto.AuthResponse;
import com.modern.banking.app.usermanagement.dto.LoginDto;
import com.modern.banking.app.usermanagement.model.RefreshToken;
import com.modern.banking.app.usermanagement.model.User;
import com.modern.banking.app.usermanagement.repository.UserRepository;
import com.modern.banking.app.usermanagement.repository.RefreshTokenRepository;
import com.modern.banking.app.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final RefreshTokenRepository refreshTokenRepository;

	public AuthResponse authenticateUser(LoginDto loginDTO) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		User user = userRepository.findByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		String jwtToken = jwtUtil.generateToken(user.getEmail());
		String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
		
		// Store refresh token in the database
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setUser(user);
        refreshTokenEntity.setRefreshToken(refreshToken);
        refreshTokenEntity.setExpiryDate(LocalDateTime.now().plusDays(30));  // Set expiry for refresh token
        refreshTokenRepository.save(refreshTokenEntity);

        return new AuthResponse(jwtToken, refreshToken);
	}
	
	// Refresh method to generate new JWT token using refresh token
    public AuthResponse refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);
        
        if (username == null) {
            throw new RuntimeException("Invalid refresh token");
        }

        // Validate refresh token in DB
        RefreshToken storedRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
        
        if (storedRefreshToken == null || storedRefreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired or invalid");
        }

        String jwtToken = jwtUtil.generateToken(username);

        return new AuthResponse(jwtToken, refreshToken);
    }

}
