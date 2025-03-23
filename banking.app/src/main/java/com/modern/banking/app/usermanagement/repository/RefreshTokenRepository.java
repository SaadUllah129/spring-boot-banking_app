package com.modern.banking.app.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modern.banking.app.usermanagement.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	RefreshToken findByRefreshToken(String refreshToken);
}
