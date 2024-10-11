package com.hoteleria_app.hoteleria_app.dto.Room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RequestUpdateRoomDto {
    @NotNull(message = "Id_number is required")
    private String id_room;

    @NotNull(message = "Room_number is required")
    @NotEmpty(message = "Room_number is required")
    private String room_numbre;

    @NotNull(message = "Avaliable is required")
    @NotEmpty(message = "Avaliable is required")
    private double avaliable;

    @NotNull(message = "Price is required")
    @NotEmpty(message = "Price is required")
    private double price ;

    @NotNull(message = "Id_room_type is required")
    private int id_room_type;

    @NotNull(message = "Id_hotel is required")
    private Float id_hotel;

    @NotNull(message = "Activate is required")
    @NotEmpty(message = "Activate is required")
    private int activate;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;
}
