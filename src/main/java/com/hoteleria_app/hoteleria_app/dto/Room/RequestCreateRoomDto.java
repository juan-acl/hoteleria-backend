package com.hoteleria_app.hoteleria_app.dto.Room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateRoomDto {
        @NotNull(message = "Room_number is required")
    @NotEmpty(message = "Room_number is required")
    private int room_number;

    @NotNull(message = "Available is required")
    @NotEmpty(message = "Available is required")
    private int available;

    @NotNull(message = " ")
    @NotEmpty(message = " ")
    private double price;

    @NotNull(message = " ")
    @NotEmpty(message = " ")
    private int id_room_type;

    @NotNull(message = " ")
    @NotEmpty(message = " ")
    private int id_hotel;

    @NotNull(message = " ")
    @NotEmpty(message = " ")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private int active;  
}
