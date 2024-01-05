package com.example.security.auth;

import com.example.security.component.ConvertDateTime;
import com.example.security.source.enums.Role;
import com.example.security.source.entity.User;
import com.example.security.source.repo.UserRepo;
import com.example.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private boolean existsByEmail(String email){
        return userRepo.existsByEmail(email);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        if(existsByEmail(request.getEmail())){
            return new AuthenticationResponse("this email already registered.", ConvertDateTime.convertDateToLocalDateTime(new Date().toString()));
        }
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var expiration = jwtService.getExpirationDate(jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
//                .expiration(String.valueOf(expiration))
                .expiration(ConvertDateTime.convertDateToLocalDateTime(expiration.toString()))
                .build();
    }
}
