package it.unicam.cs.ids.TerraViva.Security.Authentication;

import it.unicam.cs.ids.TerraViva.Exceptions.ExistingUserException;
import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(RegisterRequest request) {

        if (usersRepository.existsById(request.getUsername())) throw new ExistingUserException();

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getEmail(), Role.AUTHORIZED_TOURIST);
        usersRepository.save(user);

        return new AuthenticationResponse(jwtService.generateToken(user));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = usersRepository.findByUsername(request.getUsername()).orElseThrow();

        return new AuthenticationResponse(jwtService.generateToken(user));
    }
}