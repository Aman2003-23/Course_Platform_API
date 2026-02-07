package com.example.Assignment.DTO.auth;

public record AuthResponseDto(
        String token,
        String email,
        long expiresIn
) {}

