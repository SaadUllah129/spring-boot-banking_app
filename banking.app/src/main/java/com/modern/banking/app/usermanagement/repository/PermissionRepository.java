package com.modern.banking.app.usermanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modern.banking.app.usermanagement.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, UUID>{

}
