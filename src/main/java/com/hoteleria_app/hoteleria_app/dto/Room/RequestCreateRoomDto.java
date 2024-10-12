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
    @NotNull(message = "Room number is required")
    @NotEmpty(message = "Room number is required")
    private Integer roomNumber;

    @NotNull(message = "Avaliable is required")
    @NotEmpty(message = "Avaliable is required")
    private Integer avaliable;

    @NotNull(message = "Name hotel is required")
    @NotEmpty(message = "Name hotel is required")
    private String name;

    @NotNull(message = "Price is required")
    @NotEmpty(message = "Price is required")
    private Float price ;

    @NotNull(message = "Id_room_type is required")
    private Long id_room_type;

    @NotNull(message = "Id_hotel is required")
    private Long id_hotel;

}
