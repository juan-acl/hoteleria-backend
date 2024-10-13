package com.hoteleria_app.hoteleria_app.dto.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ResponseCreateReservationForHtml {
    public Float totalPrice;
    public List<RoomsDtoForEmail> roomsDtoForEmails;
}
