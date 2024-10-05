package com.hoteleria_app.hoteleria_app.dto.RoomType;

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
public class RequestCreateRoomTypeDto {
    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotEmpty(message = "Description is required")
    @Size(max = 200, message = "Description must be less than 200 characters")
    private String description;

    @NotEmpty(message = "Amenities is required")
    @Size(max = 200, message = "Amenities must be less than 200 characters")
    private String amenities;

    @NotNull(message = "Maximum people is required")
    @Min(value = 1, message = "Maximum people must be greater than 0")
    private Integer maximum_people;
}
