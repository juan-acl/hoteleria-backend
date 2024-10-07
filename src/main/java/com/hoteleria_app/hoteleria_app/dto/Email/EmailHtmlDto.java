package com.hoteleria_app.hoteleria_app.dto.Email;

import com.hoteleria_app.hoteleria_app.dto.Reservation.RoomReservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailHtmlDto {
    public String totalPrice;
    public List<RoomReservation> descriptions;
}
