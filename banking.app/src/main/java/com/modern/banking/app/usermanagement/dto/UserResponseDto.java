package com.modern.banking.app.usermanagement.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
	private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<String> roles;
}
