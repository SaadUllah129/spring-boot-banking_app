package com.modern.banking.app.usermanagement.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "usermanagement")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(name = "firstname", nullable = false, length = 50)
	private String firstName;
	@Column(name = "lastname", nullable = false, length = 50)
	private String lastName;
	@Email
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	@Column(nullable = false, unique = true, length = 50)
	private String phone;
	@Column(nullable = false)
	private String password;

	// Unidirectional Many-to-Many relationship
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", schema = "usermanagement", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<String> authorities = new HashSet<String>();
		roles.forEach(role -> {
			authorities.add("ROLE_"+role.getName());
			role.getPermissions().forEach(permission ->
            authorities.add(permission.getName()));
		});
			
		return authorities
				.stream()
				.map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
