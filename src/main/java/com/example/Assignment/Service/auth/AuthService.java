package com.example.Assignment.Service.auth;

import com.example.Assignment.DTO.auth.AuthResponseDto;
import com.example.Assignment.DTO.auth.LoginRequestDto;
import com.example.Assignment.DTO.auth.RegisterRequestDto;

public interface AuthService {

    void register(RegisterRequestDto dto);

    AuthResponseDto login(LoginRequestDto dto);
}
