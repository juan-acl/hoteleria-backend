package com.hoteleria_app.hoteleria_app.service.Hotel;

import org.springframework.stereotype.Service;
import java.util.*;
import com.hoteleria_app.hoteleria_app.dto.HotelDto.RequestCreateHotelDto;
import com.hoteleria_app.hoteleria_app.model.Hotel.HotelModel;
import com.hoteleria_app.hoteleria_app.repository.Hotel.HotelRepository;
import jakarta.transaction.Transactional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelModel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public HotelModel getHotelById(Long id_hotel) {
        return hotelRepository.findById(id_hotel).orElse(null);
    }

    public HotelModel createHotel(RequestCreateHotelDto hotel) {
        HotelModel newHotel = new HotelModel();
        newHotel.setName(hotel.getName().trim());
        newHotel.setEmail(hotel.getEmail().trim());
        newHotel.setAddress(hotel.getAddress().trim());
        newHotel.setPhone(hotel.getPhone().trim());
        newHotel.setRating(hotel.getRating().floatValue());
        newHotel.setDescription(hotel.getDescription().trim());
        newHotel.setStatus(1);
        return hotelRepository.save(newHotel);
    }

    public HotelModel updateHotel(HotelModel hotel) {
        return hotelRepository.save(hotel);
    }

    public HotelModel findByEmail(String email) {
        return hotelRepository.findByEmail(email);
    }

    @Transactional
    public int deleteHotel(Long id_hotel, Integer status) {
        return hotelRepository.updateStatus(id_hotel, status);
    }

    @Transactional
    public int reactivateHotel(Long id_hotel, Integer status) {
        return hotelRepository.updateStatus(id_hotel, status);
    }
}
