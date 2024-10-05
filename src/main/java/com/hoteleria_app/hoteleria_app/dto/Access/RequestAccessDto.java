package com.hoteleria_app.hoteleria_app.dto.Access;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAccessDto {
    @NotNull(message = "Id user is required")
    private Long id_user;
}
