package com.hoteleria_app.hoteleria_app.dto.Access;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestAssignmentAccess {
    @NotNull(message = "Id user is required")
    private Long id_user;

    @NotNull(message = "Id hotel is required")
    private Long id_hotel;
}
