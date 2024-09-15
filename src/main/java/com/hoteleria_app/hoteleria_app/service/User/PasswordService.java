package com.hoteleria_app.hoteleria_app.service.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor for PasswordService
     */
    public PasswordService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Encode a password using BCryptPasswordEncoder
     * 
     * @param password
     * @return encoded password
     */
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Match a password with an encoded password using BCryptPasswordEncoder
     * 
     * @param password
     * @param encodedPassword
     * @return true if the password matches, false otherwise
     */
    public boolean matchPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
