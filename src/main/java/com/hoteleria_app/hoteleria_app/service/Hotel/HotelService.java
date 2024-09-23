package com.hoteleria_app.hoteleria_app.service.Hotel;

import org.springframework.stereotype.Service;
import java.util.*;

import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.repository.Hotel.HotelRepository;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelModel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
