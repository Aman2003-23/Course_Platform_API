package com.example.Assignment.DTO.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(
        @Email String email,
        @NotBlank String password
) {}
