package com.hoteleria_app.hoteleria_app.service.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hoteleria_app.hoteleria_app.dto.UserDto.UserRequestCreateUser;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.repository.User.UserRepository;
import com.hoteleria_app.hoteleria_app.service.User.PasswordService;
import com.hoteleria_app.hoteleria_app.service.User.UserService;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordService passwordService,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public UserModel authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password));

        UserModel user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public UserModel register(UserRequestCreateUser userRequest) {
        UserModel userModel = new UserModel();
        userModel.setName(userRequest.getName());
        userModel.setLastname(userRequest.getLastname());
        userModel.setEmail(userRequest.getEmail());
        userModel.setPhone(userRequest.getPhone());
        userModel.setPassword(passwordService.encodePassword(userRequest.getPassword()));
        return userRepository.save(userModel);
    }
}
