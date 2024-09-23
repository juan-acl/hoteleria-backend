package com.hoteleria_app.hoteleria_app.controller.User;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;
import com.hoteleria_app.hoteleria_app.dto.UserDto.AllUsersResponse;
import com.hoteleria_app.hoteleria_app.dto.UserDto.DeleteUser;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserRequest;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserResponse;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserResponseDto;
import com.hoteleria_app.hoteleria_app.model.Permission.PermissionModel;
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

    @PostMapping("/deleteUser")
    public ResponseEntity<UserResponse> deleteUser(@RequestBody DeleteUser id) {
        try {
            Long idUser = id.getId_user();
            if (idUser == null) {
                return ResponseEntity.status(400)
                        .body(new UserResponse("error", "Id_user is required", 0, null));
            }
            userService.deleteUser(idUser);
            return ResponseEntity.status(200).body(new UserResponse("success", "User deleted successfully", 1, null));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new UserResponse("error", "Internal server error: " + e.getMessage(), 0, null));
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserModel user) {
        try {
            if (user.getId_user() == null) {
                return ResponseEntity.status(400)
                        .body(new UserResponse("error", "Id_user is required", 0, null));
            }
            UserModel existingUser = userService.findById(user.getId_user());
            if (existingUser == null) {
                return ResponseEntity.status(404)
                        .body(new UserResponse("error", "User not found", 0, null));
            }

            existingUser.setName(user.getName());
            existingUser.setLastname(user.getLastname());
            existingUser.setPhone(user.getPhone());
            UserModel updatedUser = userService.updateUser(existingUser);
            return ResponseEntity.status(200)
                    .body(new UserResponse("success", "User updated successfully", 1, updatedUser));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new UserResponse("error", "Internal server error: " + e.getMessage(), 0, null));
        }
    }

    @PostMapping("/me")
    public ResponseEntity<UserResponseDto> getMe() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserModel me = (UserModel) authentication.getPrincipal();
            Set<PermissionModel> permissions = userService.findByIdWithPermissions(me.getId_user());
            me.setPermisos(permissions);
            return ResponseEntity.status(200)
                    .body(new UserResponseDto("success", "User found", 1, me));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new UserResponseDto("error", "Internal server error: " + e.getMessage(), 0, null));
        }
    }
}