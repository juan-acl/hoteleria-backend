package com.hoteleria_app.hoteleria_app.dto.UserDto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestCreateUser {
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "LastName is required")
    private String lastname;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Phone is required")
    private String phone;
    @NotEmpty(message = "Password is required")
    private String password;
}
