package com.modern.banking.app.usermanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.modern.banking.app.usermanagement.model.Permission;
import com.modern.banking.app.usermanagement.repository.PermissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionService {
	private final PermissionRepository permissionRepository;

	public List<Permission> getAllPermissions() {
		return permissionRepository.findAll();
	}

	public Optional<Permission> getPermissionById(UUID id) {
		return permissionRepository.findById(id);
	}

	public Permission createPermission(Permission permission) {
		return permissionRepository.save(permission);
	}

	public Permission updatePermission(UUID id, Permission updatedPermission) {
		return permissionRepository.findById(id).map(permission -> {
			permission.setName(updatedPermission.getName());
			return permissionRepository.save(permission);
		}).orElseThrow(() -> new RuntimeException("Permission not found"));
	}

	public void deletePermission(UUID id) {
		permissionRepository.deleteById(id);
	}
}
