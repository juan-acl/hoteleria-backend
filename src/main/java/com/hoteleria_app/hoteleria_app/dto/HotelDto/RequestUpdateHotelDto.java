package com.hoteleria_app.hoteleria_app.dto.HotelDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateHotelDto {
    @NotNull(message = "Id_hotel is required")
    private Long id_hotel;

    @NotEmpty(message = "Name is required")
    @NotNull(message = "Name is required")
    private String name;

    @NotEmpty(message = "Description is required")
    @NotNull(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @NotEmpty(message = "Address is required")
    @NotNull(message = "Address is required")
    private String address;

    @NotEmpty(message = "Phone is required")
    @NotNull(message = "Phone is required")
    @Size(max = 10, message = "Phone must be 10 characters")
    private String phone;

    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be greater than 0")
    @Max(value = 10, message = "Rating must be less than 5")
    private Float rating;

}
