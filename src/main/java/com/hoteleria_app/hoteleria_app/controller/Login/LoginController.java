package com.hoteleria_app.hoteleria_app.controller.Login;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoteleria_app.hoteleria_app.dto.Login.LoginResponse;
import com.hoteleria_app.hoteleria_app.model.User.UserModel;
import com.hoteleria_app.hoteleria_app.service.Auth.AuthenticationService;
import com.hoteleria_app.hoteleria_app.utils.JwtService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        try {
            UserModel userAuth = authenticationService.authenticate(email, password);
            String jwtToken = jwtService.generateToken(userAuth);
            return ResponseEntity.ok(new LoginResponse(HttpStatus.OK.value(), "Login successful", jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(HttpStatus.UNAUTHORIZED.value(),
                            "Invalid username or password: " + e.getMessage() + " " + e.getCause(), null));
        }
    }
}
