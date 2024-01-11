package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Exceptions.ExistingUserException;
import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import it.unicam.cs.ids.TerraViva.Security.Authentication.LoginRequest;
import it.unicam.cs.ids.TerraViva.Security.Authentication.AuthenticationResponse;
import it.unicam.cs.ids.TerraViva.Security.Authentication.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {

        if (usersRepository.existsById(request.username())) throw new ExistingUserException();

        User user = new User(request.username(), passwordEncoder.encode(request.password()), request.email(), Role.AUTHORIZED_TOURIST);
        usersRepository.save(user);

        return new AuthenticationResponse(jwtService.generateToken(user));
    }

    public AuthenticationResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = usersRepository.findByUsername(request.username()).orElseThrow();

        return new AuthenticationResponse(jwtService.generateToken(user));
    }
}