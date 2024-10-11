package com.hoteleria_app.hoteleria_app.dto.Room;

import java.util.*;
import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetAllRoomDto {
      private int status;
    private String message;
    private int count;
    private List<HotelModel> rooms;
}
