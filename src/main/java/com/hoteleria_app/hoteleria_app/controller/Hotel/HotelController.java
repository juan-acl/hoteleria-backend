package com.hoteleria_app.hoteleria_app.controller.Hotel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.hoteleria_app.hoteleria_app.dto.HotelDto.GetAllHotelDto;
import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.service.Hotel.HotelService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/getAllHotels")
    public GetAllHotelDto getAllHotels() {
        try {
            List<HotelModel> hotels = hotelService.getAllHotels();
            return new GetAllHotelDto(200, "Success get all hotels", hotels.size(), hotels);
        } catch (Exception error) {
            return new GetAllHotelDto(500, "Internal server error: " + error.getMessage(), 0, null);
        }
    }

}
