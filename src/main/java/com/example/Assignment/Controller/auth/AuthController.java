package com.example.Assignment.Controller.auth;

import com.example.Assignment.DTO.auth.AuthResponseDto;
import com.example.Assignment.DTO.auth.LoginRequestDto;
import com.example.Assignment.DTO.auth.RegisterRequestDto;
import com.example.Assignment.Service.auth.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Map<String, Object> register(
            @RequestBody @Valid RegisterRequestDto dto) {

        authService.register(dto);

        return Map.of(
                "email", dto.email(),
                "message", "User registered successfully"
        );
    }

    @PostMapping("/login")
    public AuthResponseDto login(
            @RequestBody LoginRequestDto dto) {

        return authService.login(dto);
    }
}

