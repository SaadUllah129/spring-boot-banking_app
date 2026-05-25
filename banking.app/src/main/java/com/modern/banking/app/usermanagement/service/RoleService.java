package com.modern.banking.app.usermanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.modern.banking.app.usermanagement.exception.CustomException;
import com.modern.banking.app.usermanagement.model.Role;
import com.modern.banking.app.usermanagement.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(UUID id) {
        return roleRepository.findById(id)
        		.orElseThrow(() ->
        		new CustomException("Role not found", HttpStatus.NOT_FOUND));
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(UUID id, Role updatedRole) {
        return roleRepository.findById(id).map(role -> {
            role.setName(updatedRole.getName());
            role.setPermissions(updatedRole.getPermissions());
            return roleRepository.save(role);
        }).orElseThrow(() -> new CustomException("Role not found",HttpStatus.NOT_FOUND));
    }

    public void deleteRole(UUID id) {
        roleRepository.deleteById(id);
    }
}
