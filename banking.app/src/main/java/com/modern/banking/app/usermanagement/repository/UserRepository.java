package com.modern.banking.app.usermanagement.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.modern.banking.app.usermanagement.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
	
	@Query("""
	        SELECT DISTINCT u FROM User u
	        LEFT JOIN FETCH u.roles r
	        LEFT JOIN FETCH r.permissions
	        WHERE u.email = :email
	        """)
	Optional<User> findByEmailWithRolesAndPermissions(String email);
}
