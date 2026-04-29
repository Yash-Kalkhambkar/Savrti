package com.savrti.plantexchange.service;

import com.savrti.plantexchange.dto.AuthResponse;
import com.savrti.plantexchange.dto.LoginRequest;
import com.savrti.plantexchange.dto.RegisterRequest;
import com.savrti.plantexchange.dto.UserResponseDTO;
import com.savrti.plantexchange.entity.User;
import com.savrti.plantexchange.entity.UserType;
import com.savrti.plantexchange.exception.UserAlreadyExistsException;
import com.savrti.plantexchange.repository.UserRepository;
import com.savrti.plantexchange.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                UserType.MEMBER
        );

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponse(token, new UserResponseDTO(user));
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponse(token, new UserResponseDTO(user));
    }
}
