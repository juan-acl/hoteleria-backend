package com.hoteleria_app.hoteleria_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Securityconfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactivar CSRF temporalmente (activar en producción)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll() // Permitir acceso público al login
                                                                   // y registro
                        .anyRequest().authenticated() // Proteger las demás rutas
                )
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
}
