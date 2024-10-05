package com.hoteleria_app.hoteleria_app.dto.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReservationDto {
    private String status;
    private String message;
}
