package com.hoteleria_app.hoteleria_app.dto.PermisosDto;

import java.util.Set;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermisoResponseDto {
    private Long id_user;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private Set<PermisosDto> permisos;
}
