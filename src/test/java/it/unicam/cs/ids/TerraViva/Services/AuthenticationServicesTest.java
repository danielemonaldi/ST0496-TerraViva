package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import it.unicam.cs.ids.TerraViva.Security.Authentication.LoginRequest;
import it.unicam.cs.ids.TerraViva.Security.Authentication.AuthenticationResponse;
import it.unicam.cs.ids.TerraViva.Security.Authentication.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationServicesTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationServices authenticationServices;

    @Test
    @DirtiesContext
    @Transactional
    void testRegister() {

        // Register new account
        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);

        // Verification
        assertTrue(usersRepository.existsById("testUser"));
    }

    @Test
    @Transactional
    @DirtiesContext
    void testAuthenticate() {

        // Register new account
        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);

        // User login
        AuthenticationResponse response = authenticationServices.login(new LoginRequest("testUser", "password"));

        // Verification
        assertNotNull(response.token());
        assertNotEquals("", response.token());
    }
}