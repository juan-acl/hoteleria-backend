package com.hoteleria_app.hoteleria_app.controller.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hoteleria_app.hoteleria_app.dto.UserDto.AllUsersResponse;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserRequest;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserResponse;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.service.User.UserService;

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
}
