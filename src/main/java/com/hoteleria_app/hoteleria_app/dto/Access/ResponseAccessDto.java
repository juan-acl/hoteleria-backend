package com.hoteleria_app.hoteleria_app.dto.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAccessDto {
    private String status;
    private String message;
}
