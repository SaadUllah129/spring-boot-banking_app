package com.modern.banking.app.usermanagement.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modern.banking.app.usermanagement.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID>{
	Optional<Role> findByName(String name);
}
