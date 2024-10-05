package com.hoteleria_app.hoteleria_app.dto.HotelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHotelDto {
    private int status;
    private String message;
}
