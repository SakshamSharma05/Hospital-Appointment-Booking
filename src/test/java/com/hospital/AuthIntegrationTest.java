package com.hospital;

import com.hospital.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthIntegrationTest {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void adminUserShouldAuthenticateWithDefaultPassword() {
        assertThat(userRepository.findByUsername("admin")).isPresent();

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("admin", "admin123"));

        assertThat(auth.isAuthenticated()).isTrue();
    }
}
