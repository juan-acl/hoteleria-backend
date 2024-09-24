package com.hoteleria_app.hoteleria_app.dto.HotelDto;

import jakarta.validation.constraints.NotNull;

public class RequestGetHotelByIdDto {

    @NotNull(message = "id_hotel is required")
    private Long id_hotel;

    public Long getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(Long id_hotel) {
        this.id_hotel = id_hotel;
    }
}