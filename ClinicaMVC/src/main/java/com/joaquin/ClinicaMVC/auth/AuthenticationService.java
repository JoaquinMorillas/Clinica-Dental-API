package com.joaquin.ClinicaMVC.auth;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joaquin.ClinicaMVC.configuration.JwtService;
import com.joaquin.ClinicaMVC.entity.Role;
import com.joaquin.ClinicaMVC.entity.User;
import com.joaquin.ClinicaMVC.exception.EmailAlreadyRegistered;
import com.joaquin.ClinicaMVC.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) throws EmailAlreadyRegistered{
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyRegistered("El correo ya esta registrado");
        }

        var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate( 
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), 
                request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow();

        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwt).build();

    }
}
