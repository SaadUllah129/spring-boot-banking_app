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

import com.modern.banking.app.usermanagement.model.Role;
import com.modern.banking.app.usermanagement.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usermanagement/api/v1/role")
public class RoleController {
	private final RoleService roleService;

	@GetMapping
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@GetMapping("/{id}")
	public Role getRoleById(@PathVariable UUID id) {
		return roleService.getRoleById(id);
	}

	@PostMapping
	public Role createRole(@RequestBody Role role) {
		return roleService.createRole(role);
	}

	@PutMapping("/{id}")
	public Role updateRole(@PathVariable UUID id, @RequestBody Role role) {
		return roleService.updateRole(id, role);
	}

	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable UUID id) {
		roleService.deleteRole(id);
	}
}
