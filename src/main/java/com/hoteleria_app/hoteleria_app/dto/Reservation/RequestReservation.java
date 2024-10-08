package com.hoteleria_app.hoteleria_app.dto.Reservation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestReservation {
    @NotNull(message = "id_user is required")
    public Long id_user;

    @Valid
    @NotNull(message = "room_reservations is required")
    @Size(min = 1, message = "room_reservations must have at least one element")
    public RoomReservation[] room_reservations;
}
