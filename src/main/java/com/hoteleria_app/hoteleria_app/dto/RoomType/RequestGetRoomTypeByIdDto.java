package com.hoteleria_app.hoteleria_app.dto.RoomType;

import jakarta.validation.constraints.NotNull;

public class RequestGetRoomTypeByIdDto {

    @NotNull(message = "id_room_type is required")
    private Long id_room_type;

    public Long getId_room_type() {
        return id_room_type;
    }

    public void setId_room_type(Long id_room_type) {
        this.id_room_type = id_room_type;
    }
}
