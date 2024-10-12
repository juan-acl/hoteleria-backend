package com.hoteleria_app.hoteleria_app.dto.Room;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RequestGetRoomByldDto {
    @NotNull(message = "id_Room is required")
    private Long id_room;
}
