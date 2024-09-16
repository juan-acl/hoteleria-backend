package com.hoteleria_app.hoteleria_app.dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private int status;
    private String message;
    private String token;
}
