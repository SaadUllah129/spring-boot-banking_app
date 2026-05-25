package com.modern.banking.app.usermanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.modern.banking.app.usermanagement.dto.PermissionRequest;
import com.modern.banking.app.usermanagement.exception.CustomException;
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

	public Permission getPermissionById(UUID id) {
		return permissionRepository.findById(id)
				.orElseThrow(() -> 
				new CustomException("Permission not found", HttpStatus.NOT_FOUND));
	}

	public Permission createPermission(PermissionRequest request) {
		 Permission permission = new Permission();
		    permission.setName(request.getName());
		    return permissionRepository.save(permission);
	}

	public Permission updatePermission(UUID id, PermissionRequest request) {
		return permissionRepository.findById(id).map(permission -> {
			permission.setName(request.getName());
			return permissionRepository.save(permission);
		})
				.orElseThrow(() -> 
				new CustomException("Permission not found", HttpStatus.NOT_FOUND));
	}

	public void deletePermission(UUID id) {
		permissionRepository.deleteById(id);
	}
}
