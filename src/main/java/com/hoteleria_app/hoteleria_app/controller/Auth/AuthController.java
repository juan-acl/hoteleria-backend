package com.hoteleria_app.hoteleria_app.controller.Auth;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoteleria_app.hoteleria_app.dto.Login.LoginResponse;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserRequestCreateUser;
import com.hoteleria_app.hoteleria_app.dto.UserDto.UserResponse;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.service.Auth.AuthenticationService;
import com.hoteleria_app.hoteleria_app.service.User.UserService;
import com.hoteleria_app.hoteleria_app.utils.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthController(UserService userService, AuthenticationService authenticationService, JwtService jwtService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        try {
            UserModel userAuth = authenticationService.authenticate(email, password);
            String jwtToken = jwtService.generateToken(userAuth);
            return ResponseEntity
                    .ok(new LoginResponse(HttpStatus.OK.value(), "Login successful", jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(HttpStatus.UNAUTHORIZED.value(),
                            "Invalid username or password: " + e.getMessage() + " " + e.getCause(),
                            null));
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequestCreateUser user,
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
            if (findUser != null) {
                return ResponseEntity.status(400)
                        .body(new UserResponse("error", "User already exists", 0, null));
            }

            UserModel newUser = authenticationService.register(user);
            return ResponseEntity.status(200)
                    .body(new UserResponse("success", "User created successfully", 1, newUser));

        } catch (Exception error) {
            return ResponseEntity.status(500).body(new UserResponse("error",
                    "Internal server error: " + error.getMessage(), 0, null));
        }
    }
}
