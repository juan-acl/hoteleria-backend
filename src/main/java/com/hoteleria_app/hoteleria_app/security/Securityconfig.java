package com.hoteleria_app.hoteleria_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hoteleria_app.hoteleria_app.service.User.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactivar CSRF temporalmente (activar en producción)
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/api/login", "/api/user/createUser").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin()
                .usernameParameter("email") // Cambiar el nombre del campo de usuario a "email"
                .passwordParameter("password") // Cambiar el nombre del campo de contraseña
                .permitAll(); // Permitir acceso público al formulario de login

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }
}