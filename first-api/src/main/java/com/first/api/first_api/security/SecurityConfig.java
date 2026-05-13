package com.first.api.first_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF porque es una API REST
            .authorizeHttpRequests(auth -> auth
                // Reglas: GET para cualquiera logueado (USER o ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")
                // Reglas: POST, PUT, DELETE exclusivo para ADMIN
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                
                // Permitir acceso estático al frontend de prueba sin login
                .requestMatchers("/", "/index.html").permitAll()
                
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // Usamos Autenticación Básica (usuario y contraseña en el header)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Encriptador oficial de Spring
        return new BCryptPasswordEncoder();
    }
}