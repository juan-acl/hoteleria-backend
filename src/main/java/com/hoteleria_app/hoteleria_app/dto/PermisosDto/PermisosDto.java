package com.hoteleria_app.hoteleria_app.dto.PermisosDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermisosDto {
    private String name;
    private int delete;
    private int update;
    private int create;
    private int view;
    private int report;
}
