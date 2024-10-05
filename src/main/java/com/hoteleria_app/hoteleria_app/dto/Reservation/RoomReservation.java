package com.hoteleria_app.hoteleria_app.dto.Reservation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomReservation {
    @NotNull(message = "initial_reservation_date is required")
    public LocalDateTime initial_reservation_date;

    @NotNull(message = "final_reservation_date is required")
    public LocalDateTime final_reservation_date;

    @NotNull(message = "id_room is required")
    public Long id_room;
}
