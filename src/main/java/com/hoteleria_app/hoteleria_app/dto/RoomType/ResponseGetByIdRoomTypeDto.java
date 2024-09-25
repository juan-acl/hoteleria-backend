package com.hoteleria_app.hoteleria_app.dto.RoomType;

import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetByIdRoomTypeDto {

    private String status;
    private String message;
    private RoomTypeModel roomType;
}
