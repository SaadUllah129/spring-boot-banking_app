package com.modern.banking.app.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modern.banking.app.usermanagement.model.RefreshToken;
import com.modern.banking.app.usermanagement.model.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	RefreshToken findByRefreshToken(String refreshToken);
	
	void deleteAllByUser(User user);
	
}
