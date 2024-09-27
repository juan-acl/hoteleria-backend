package com.hoteleria_app.hoteleria_app.dto.RoomType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RequestUpdateRoomTypeDto {
    @NotNull(message = "Id_room_type is required")
    private Long id_room_type;

    @NotEmpty(message = "Name is required")
    @NotNull(message = "Name is required")
    private String name;

    @NotEmpty(message = "Description is required")
    @NotNull(message = "Description is required")
    private String description;

    @NotEmpty(message = "Amenities is required")
    @NotNull(message = "Amenities is required")
    private String amenities;

    @NotNull(message = "Maximum people is required")
    private int maximum_people;
}
