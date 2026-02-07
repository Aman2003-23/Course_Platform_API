package com.example.Assignment.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.addAllowedOriginPattern("*"); // allow all origins
    configuration.addAllowedHeader("*");         // allow all headers
    configuration.addAllowedMethod("*");         // allow all HTTP methods (GET, POST, etc.)

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // apply to all endpoints
    return source;
}

