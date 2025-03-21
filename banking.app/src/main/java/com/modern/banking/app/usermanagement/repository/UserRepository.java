package com.modern.banking.app.usermanagement.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modern.banking.app.usermanagement.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findByEmail(String email);
}
