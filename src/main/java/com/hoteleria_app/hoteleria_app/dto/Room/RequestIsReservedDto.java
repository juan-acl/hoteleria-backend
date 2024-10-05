package com.hoteleria_app.hoteleria_app.dto.Room;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestIsReservedDto {
    public Long id;
    public LocalDateTime initialReservationDate;
    public LocalDateTime finalReservationDate;
}
