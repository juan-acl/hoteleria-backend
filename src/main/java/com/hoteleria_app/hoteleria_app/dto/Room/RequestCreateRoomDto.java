package com.hoteleria_app.hoteleria_app.dto.Room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateRoomDto {
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

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private int activate;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private int name;
}
