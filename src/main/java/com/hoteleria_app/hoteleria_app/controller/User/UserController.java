package com.hoteleria_app.hoteleria_app.controller.User;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.hoteleria_app.hoteleria_app.dto.UserDto.AllUsersResponse;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserRequest;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserRequestCreateUser;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserResponse;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.service.User.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/getUserByEmail")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestBody UserRequest email) {
        try {
            if (email.getEmail() == null || email.getEmail().isEmpty()) {
                return ResponseEntity.status(300).body(new UserResponse("error", "All params are required!!", 0, null));
            }
            UserModel findUser = userService.findByEmail(email.getEmail());
            if (findUser == null) {
                return ResponseEntity.status(404).body(new UserResponse("error", "User not found", 0, null));
            }
            return ResponseEntity.status(200).body(new UserResponse("success", "User found", 1, findUser));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new UserResponse("error", "Internal server error: " + e.getMessage(), 0, null));
        }
    }

    @PostMapping("/getAllUsers")
    public ResponseEntity<AllUsersResponse> getAllUsers() {
        try {
            List<UserModel> users = userService.getAllUsers();
            return ResponseEntity.status(200).body(new AllUsersResponse("success", "Users found", users.size(), users));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new AllUsersResponse("error", "Internal server error: " + e.getMessage(), 0, null));
        }
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequestCreateUser user,
            BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder();
                bindingResult.getAllErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                });

                return ResponseEntity.status(400)
                        .body(new UserResponse("error", errorMessage.toString(), 0, null));
            }
            UserModel findUser = userService.findByEmail(user.getEmail());
            System.out.println(findUser);
            if (findUser != null) {
                return ResponseEntity.status(400)
                        .body(new UserResponse("error", "User already exists", 0, null));
            }
            UserModel newUser = userService.createUser(user);
            return ResponseEntity.status(200)
                    .body(new UserResponse("success", "User created successfully", 1, newUser));
        } catch (Exception error) {
            return ResponseEntity.status(500)
                    .body(new UserResponse("error", "Internal server error: " + error.getMessage(), 0, null));
        }
    }
}
