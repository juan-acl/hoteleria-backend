package com.hoteleria_app.hoteleria_app.dto.UserDto;

import java.util.Set;

import com.hoteleria_app.hoteleria_app.model.Permisos.PermisosModel;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String status;
    private String message;
    private int count;
    private Set<PermisosModel> currentUser;
}