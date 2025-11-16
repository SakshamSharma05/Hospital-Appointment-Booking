package com.hospital.config;

import com.hospital.entity.Role;
import com.hospital.entity.User;
import com.hospital.repository.RoleRepository;
import com.hospital.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Component
public class AdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitializer(UserRepository userRepository,
                            RoleRepository roleRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        // Ensure ADMIN role exists
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElse(null);
        if (adminRole == null) {
            return; // roles migration hasn't run or DB not ready; don't fail startup
        }

        User admin = userRepository.findByUsername("admin").orElse(null);

        if (admin == null) {
            // Create default admin user
            admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@hospital.com");
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setPhone("1234567890");
            admin.setEnabled(true);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(new HashSet<>());
            admin.getRoles().add(adminRole);
            userRepository.save(admin);
            return;
        }

        // If admin exists but password does NOT match admin123, reset it so demo credentials always work
        if (!passwordEncoder.matches("admin123", admin.getPassword())) {
            admin.setPassword(passwordEncoder.encode("admin123"));
        }
        admin.setEnabled(true);

        if (admin.getRoles() == null) {
            admin.setRoles(new HashSet<>());
        }
        if (admin.getRoles().stream().noneMatch(r -> "ADMIN".equals(r.getName()))) {
            admin.getRoles().add(adminRole);
        }

        userRepository.save(admin);
    }
}
