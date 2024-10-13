package com.hoteleria_app.hoteleria_app.dto.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomsDtoForEmail {
    public LocalDateTime initial_reservation_date;
    public LocalDateTime final_reservation_date;
    public Float priceRoom;
    public Integer id_room;
}
