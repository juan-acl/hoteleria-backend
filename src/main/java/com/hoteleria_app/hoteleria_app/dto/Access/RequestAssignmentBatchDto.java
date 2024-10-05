package com.hoteleria_app.hoteleria_app.dto.Access;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestAssignmentBatchDto {
    @NotNull(message = "Id hotel is required")
    @Size(min = 1, message = "At least one hotel is required")
    private List<Long> id_hotel;

    @NotNull(message = "Id user is required")
    private Long id_user;
}
