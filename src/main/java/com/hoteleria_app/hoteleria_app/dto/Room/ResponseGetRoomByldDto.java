package com.hoteleria_app.hoteleria_app.dto.Room;

import jakarta.validation.constraints.NotNull;

public class ResponseGetRoomByldDto {
        @NotNull(message = "id_Room is required")
    private Long id_room;

    public Long getId_hotel() {
        return id_room;
    }

    public void setId_room(Long id_room) {
        this.id_room = id_room;
    }
}
