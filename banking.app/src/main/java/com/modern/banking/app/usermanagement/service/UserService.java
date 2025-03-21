package com.modern.banking.app.usermanagement.service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.modern.banking.app.usermanagement.dto.UserDto;
import com.modern.banking.app.usermanagement.dto.UserResponseDto;
import com.modern.banking.app.usermanagement.model.Role;
import com.modern.banking.app.usermanagement.model.User;
import com.modern.banking.app.usermanagement.repository.RoleRepository;
import com.modern.banking.app.usermanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	public UserResponseDto registerUser(UserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPhone(userDto.getPhone());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Role role = roleRepository.findByName("Customer")
				.orElseThrow(() -> new RuntimeException("default role not found"));
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		
		userRepository.save(user);
		
		return mapToUserResponseDto(user);
		
 	}
	
	private UserResponseDto mapToUserResponseDto(User user) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setId(user.getId());
		userResponseDto.setFirstName(user.getFirstName());
		userResponseDto.setLastName(user.getLastName());
		userResponseDto.setEmail(user.getEmail());
		userResponseDto.setPhone(user.getPhone());
		userResponseDto.setRoles(user.getRoles().stream().map(Role::getName).collect(
				java.util.stream.Collectors.toSet()));
		
		return userResponseDto;
	}
	
	public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
