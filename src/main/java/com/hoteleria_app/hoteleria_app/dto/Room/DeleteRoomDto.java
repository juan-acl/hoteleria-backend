package com.hoteleria_app.hoteleria_app.dto.Room;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DeleteRoomDto {
    @NotNull(message = "Id_room is required")
    public Long id_room;
}
