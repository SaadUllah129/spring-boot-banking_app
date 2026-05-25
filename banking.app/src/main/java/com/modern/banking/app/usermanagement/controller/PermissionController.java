package com.modern.banking.app.usermanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.banking.app.usermanagement.dto.PermissionRequest;
import com.modern.banking.app.usermanagement.model.Permission;
import com.modern.banking.app.usermanagement.service.PermissionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usermanagement/api/v1/permission")
public class PermissionController {
	private final PermissionService permissionService;

	@GetMapping
	public List<Permission> getAllPermissions() {
		return permissionService.getAllPermissions();
	}

	@GetMapping("/{id}")
	public Permission getPermissionById(@PathVariable UUID id) {
		return permissionService.getPermissionById(id);
	}

	@PostMapping
	public Permission createPermission(@Valid @RequestBody PermissionRequest request) {
		return permissionService.createPermission(request);
	}

	@PutMapping("/{id}")
	public Permission updatePermission(@Valid @PathVariable("id") UUID id, @RequestBody PermissionRequest request) {
		return permissionService.updatePermission(id, request);
	}

	@DeleteMapping("/{id}")
	public void deletePermission(@PathVariable UUID id) {
		permissionService.deletePermission(id);
	}
}
