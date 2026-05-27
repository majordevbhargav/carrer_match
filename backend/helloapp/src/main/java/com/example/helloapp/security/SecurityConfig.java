package com.example.helloapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> {})

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // Allow all your APIs
                        .requestMatchers("/api/**").permitAll()

                        // Allow analytics endpoint too
                        .requestMatchers("/analytics").permitAll()

                        // Allow resume upload
                        .requestMatchers("/resume/**").permitAll()

                        // Allow everything else temporarily
                        .anyRequest().permitAll()
                )

                .build();
    }
}