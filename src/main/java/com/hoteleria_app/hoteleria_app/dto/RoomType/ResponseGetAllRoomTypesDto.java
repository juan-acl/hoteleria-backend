package com.hoteleria_app.hoteleria_app.dto.RoomType;

import java.util.List;
import com.hoteleria_app.hoteleria_app.model.RoomType.RoomTypeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetAllRoomTypesDto {
    private String status;
    private String message;
    private int count;
    private List<RoomTypeModel> roomTypes;
}
