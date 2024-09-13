package com.hoteleria_app.hoteleria_app.dto.UserDto;

import java.util.List;

import com.hoteleria_app.hoteleria_app.model.User.UserModel;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUsersResponse {
    private String status;
    private String message;
    private Integer count;
    private List<UserModel> users;
}
