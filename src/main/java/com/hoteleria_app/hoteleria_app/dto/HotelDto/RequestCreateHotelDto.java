package com.hoteleria_app.hoteleria_app.dto.HotelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateHotelDto {
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotNull(message = "Address is required")
    @NotEmpty(message = "Address is required")
    private String address;

    @NotNull(message = "Phone is required")
    @NotEmpty(message = "Phone is required")
    private String phone;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating must be greater than 0")
    @Max(value = 10, message = "Rating must be less than 5")
    private Float rating;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
}
