package com.hoteleria_app.hoteleria_app.dto.Access;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class RequestAssignmentBatchDto {
    @NotNull(message = "Id hotel is required")

    private List<Long> id_hotel;

    @NotNull(message = "Id user is required")
    private Long id_user;
}
