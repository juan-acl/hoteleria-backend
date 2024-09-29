package com.hoteleria_app.hoteleria_app.dto.Access;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDeleteAssignment {
    @NotNull(message = "Id access is required")
    private Long id_access;
}
