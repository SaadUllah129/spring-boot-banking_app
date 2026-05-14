package com.modern.banking.app.config;

import com.modern.banking.app.usermanagement.model.Role;
import com.modern.banking.app.usermanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_ADMIN");
    }

    private void createRoleIfNotExists(String roleName) {

        boolean exists = roleRepository.findByName(roleName).isPresent();

        if (!exists) {

            Role role = new Role();
            role.setName(roleName);

            roleRepository.save(role);

            System.out.println(roleName + " created");
        }
    }
}