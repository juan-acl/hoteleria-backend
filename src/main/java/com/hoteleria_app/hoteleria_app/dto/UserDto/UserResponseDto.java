package com.hoteleria_app.hoteleria_app.dto.UserDto;

import com.hoteleria_app.hoteleria_app.model.User.UserModel;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String status;
    private String message;
    private int count;
    private UserModel currentUser;
}
