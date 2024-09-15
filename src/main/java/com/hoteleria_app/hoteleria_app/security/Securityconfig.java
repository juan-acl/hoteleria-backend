package com.hoteleria_app.hoteleria_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Securityconfig {

    @SuppressWarnings({ "deprecation", "removal" })
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactivar CSRF temporalmente (activar en producción)
                .authorizeRequests()
                .requestMatchers("/api/**").permitAll() // Permitir acceso público a este endpoint
                .anyRequest().authenticated() // Proteger las demás rutas
                .and()
                .formLogin().disable(); // Desactivar login por formulario si no lo estás utilizando

        return http.build();
    }
}