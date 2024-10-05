package com.hoteleria_app.hoteleria_app.dto.Room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseRoomDto {
    public String status;
    public String message;
    public ResponseRoomReservedDto roomReserved;
}
