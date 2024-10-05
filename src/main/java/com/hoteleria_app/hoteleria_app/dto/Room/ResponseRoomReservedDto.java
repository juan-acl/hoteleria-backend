package com.hoteleria_app.hoteleria_app.dto.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRoomReservedDto {
    public Boolean isReserved;
    public Long countReserved;
}
