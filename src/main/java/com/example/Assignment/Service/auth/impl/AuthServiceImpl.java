package com.example.Assignment.Service.auth.impl;

import com.example.Assignment.DTO.auth.AuthResponseDto;
import com.example.Assignment.DTO.auth.LoginRequestDto;
import com.example.Assignment.DTO.auth.RegisterRequestDto;
import com.example.Assignment.Entity.auth.Role;
import com.example.Assignment.Entity.auth.User;
import com.example.Assignment.Repository.auth.UserRepository;
import com.example.Assignment.Service.auth.AuthService;
import com.example.Assignment.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;



    @Override
    public void register(RegisterRequestDto dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.STUDENT)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );

        String token = jwtService.generateToken(dto.email());

        return new AuthResponseDto(
                token,
                dto.email(),
                jwtService.getExpirationMillis()
        );
    }

}

